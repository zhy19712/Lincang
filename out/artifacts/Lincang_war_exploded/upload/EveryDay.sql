--D_DayTheogen���������۷�����	������Դ����10�������ݱ�(data_Env10minData)�е���Ӧ��б����䵱�����ۼ�ֵ��CANTOTALRADVAL������װ����������ȡ����23��50�ģ�װ����������Ŀװ����������
select to_char(a.TIMESTAMP,'yyyy-MM-dd HH24:mi:ss') as TIMESTAMP,a.CANTOTALRADVAL,d.PROJECTCODE,e.CAPACITY from
DATA_ENV10MINDATA a,(select trunc(sysdate) as time from dual) b,DEV_ENVMONITOR c,DEV_BUSLINE d,DEV_PROJECT e
where a.HOURNUM = '23' and a.MINUTENUM = '50' and a.TIMESTAMP  between b.time and (b.time + 1) and a.ENVMONITORCODE = c.ENVMONITORCODE
and c.SUBSTATIONCODE = d.SUBSTATIONCODE and c.SUBSTATIONTYPE = d.SUBSTATIONTYPE and d.PROJECTCODE = e.PROJECTCODE;

select to_char(n.TIMESTAMP,'yyyy-MM-dd HH24:mi:ss'),n.YEARNUM,n.MONTHNUM,n.DAYNUM,n.DATAVALUE,n.PROJECTCODE,n.PROJECTNAME from
(select m.*,row_number() over (partition by m.PROJECTCODE,to_char(m.TIMESTAMP,'yyyy-MM-dd') order by m.TIMESTAMP desc) rank from 
(select a.TIMESTAMP,a.YEARNUM,a.MONTHNUM,a.DAYNUM,a.CANTOTALRADVAL*e.CAPACITY as DATAVALUE,d.PROJECTCODE,e.PROJECTNAME from
DATA_ENV10MINDATA a,DEV_ENVMONITOR c,DEV_BUSLINE d,DEV_PROJECT e
where a.TIMESTAMP >=  '2017-06-20'  and a.ENVMONITORCODE = c.ENVMONITORCODE
and c.SUBSTATIONCODE = d.SUBSTATIONCODE and c.SUBSTATIONTYPE = d.SUBSTATIONTYPE and d.PROJECTCODE = e.PROJECTCODE and d.PROJECTCODE ='1')m)n
where n.rank = 1;

--10.1�汾��
--12
select to_date(to_char(n.TIMESTAMP,'yyyy-MM-dd HH24:mi:ss')) as TIMESTAMP,TO_NUMBER(to_char(n.TIMESTAMP,'yyyy'), '9999') as YEARNUM,to_number(to_char(n.TIMESTAMP,'MM'),'99') as MONTHNUM,to_number(to_char(n.TIMESTAMP,'dd'),'99') as DAYNUM,n.D_DAYTHEOGENH,n.D_DAYTHEOGEN,n.PROJECTCODE,n.PROJECTNAME from
(select m.*,row_number() over (partition by m.PROJECTCODE,to_char(m.TIMESTAMP,'yyyy-MM-dd') order by m.TIMESTAMP desc) rank from 
(select a.TIMESTAMP,a.YEARNUM,a.MONTHNUM,a.DAYNUM,a.CANTOTALRADVAL as D_DAYTHEOGENH,a.CANTOTALRADVAL*e.CAPACITY as D_DAYTHEOGEN,d.PROJECTCODE,e.PROJECTNAME from
DATA_ENV10MINDATA a,DEV_ENVMONITOR c,DEV_BUSLINE d,DEV_PROJECT e
where a.TIMESTAMP >  to_date(to_char(to_date('2017-06-21 23:20:00','yyyy-MM-dd HH24:mi:ss'),'yyyy-MM-dd'),'yyyy-MM-dd')  and a.ENVMONITORCODE = c.ENVMONITORCODE
and c.SUBSTATIONCODE = d.SUBSTATIONCODE and c.SUBSTATIONTYPE = d.SUBSTATIONTYPE and d.PROJECTCODE = e.PROJECTCODE and d.PROJECTCODE = '1')m)n
where n.rank = 1;

--D_Daypower�ֶΣ����Ǹ�վ��װ����������������һ����Ŀ��Ӧһ��վ��˵�ġ�������ѹվ��
select to_char(max.TIMESTAMP,'yyyy-MM-dd HH24:mi:ss') as TIMESTAMP,max.PROJECTCODE,max.PROJECTNAME,max.PACTELEC - min.PACTELEC as D_Daypower,min.CAPACITY from 
(select e.PACTELEC,e.TIMESTAMP,e.PROJECTCODE,f.PROJECTNAME from
(select n.PACTELEC,n.TIMESTAMP,n.PROJECTCODE from
(select m.*,row_number() over (partition by m.PROJECTCODE order by m.TIMESTAMP desc) rank from 
(select c.PACTELEC,c.TIMESTAMP,d.PROJECTCODE from
(select a.PACTELEC,a.TIMESTAMP,b.SUBSTATIONTYPE,B.SUBSTATIONCODE from DATA_GRIDSWITCH5MINDATA a,DEV_GRIDSWITCH b,(select trunc(sysdate) as time from dual) bb
where a.GRIDSWITCHCODE = b.GRIDSWITCHCODE and a.TIMESTAMP between to_date('2000-01-01 00:00:00','yyyy-MM-dd HH24:mi:ss') and (bb.time))c,DEV_BUSLINE d,DEV_STATION g
where c.SUBSTATIONTYPE = d.SUBSTATIONTYPE and c.SUBSTATIONCODE = d.SUBSTATIONCODE and c.SUBSTATIONTYPE = 1 and c.SUBSTATIONCODE = g.STATIONCODE)m)n
where rank = 1)e,DEV_PROJECT f
where e.PROJECTCODE = f.PROJECTCODE      
)min,
(select e.PACTELEC,e.TIMESTAMP,e.PROJECTCODE,e.CAPACITY,f.PROJECTNAME from
(select n.PACTELEC,n.TIMESTAMP,n.PROJECTCODE,n.CAPACITY from
(select m.*,row_number() over (partition by m.PROJECTCODE order by m.TIMESTAMP desc) rank from 
(select c.PACTELEC,c.TIMESTAMP,d.PROJECTCODE,g.CAPACITY from
(select a.PACTELEC,a.TIMESTAMP,b.SUBSTATIONTYPE,B.SUBSTATIONCODE from DATA_GRIDSWITCH5MINDATA a,DEV_GRIDSWITCH b,(select trunc(sysdate) as time from dual) bb
where a.GRIDSWITCHCODE = b.GRIDSWITCHCODE and a.TIMESTAMP between bb.time and (bb.time + 1))c,DEV_BUSLINE d,DEV_STATION g
where c.SUBSTATIONTYPE = d.SUBSTATIONTYPE and c.SUBSTATIONCODE = d.SUBSTATIONCODE and c.SUBSTATIONTYPE = 1 and c.SUBSTATIONCODE = g.STATIONCODE)m)n
where rank = 1)e,DEV_PROJECT f
where e.PROJECTCODE = f.PROJECTCODE
)max
where min.PROJECTCODE = max.PROJECTCODE;

--10.1�汾��D_Daypower�ֶΣ�������Ŀ�ͺ��ˡ����������������Ĳ��ֵ�������������ڣ�����������Ҫ��datavalue��
select ceil(max.TIMESTAMP - min.TIMESTAMP) as DAYS,to_char(max.TIMESTAMP,'yyyy-MM-dd HH24:mi:ss') as MAXTIMESTAMP,to_char(min.TIMESTAMP,'yyyy-MM-dd HH24:mi:ss') AS MINTIMESTAMP,max.YEARNUM,max.MONTHNUM,max.DAYNUM,max.PROJECTCODE,max.PROJECTNAME,max.PACTELEC - min.PACTELEC as D_Daypower,decode(max.CAPACITY,0,0,max.CAPACITY,(max.PACTELEC - min.PACTELEC)/max.CAPACITY) as D_DAYPOWERH from 
(select e.PACTELEC,e.TIMESTAMP,e.PROJECTCODE,f.CAPACITY,f.PROJECTNAME from
(select n.PACTELEC,n.TIMESTAMP,n.PROJECTCODE from
(select m.*,row_number() over (partition by m.PROJECTCODE order by m.TIMESTAMP desc) rank from 
(select c.PACTELEC,c.TIMESTAMP,d.PROJECTCODE from
(select a.PACTELEC,a.TIMESTAMP,b.SUBSTATIONTYPE,B.SUBSTATIONCODE from DATA_GRIDSWITCH5MINDATA a,DEV_GRIDSWITCH b
where to_date('2017-08-07 00:00:00','yyyy-MM-dd HH24:mi:ss') > a.TIMESTAMP and a.GRIDSWITCHCODE = b.GRIDSWITCHCODE)c,DEV_BUSLINE d
where c.SUBSTATIONTYPE = d.SUBSTATIONTYPE and c.SUBSTATIONCODE = d.SUBSTATIONCODE and c.SUBSTATIONTYPE = d.SUBSTATIONTYPE)m)n
where rank = 1)e,DEV_PROJECT f
where e.PROJECTCODE = f.PROJECTCODE
)min,
(select e.PACTELEC,e.TIMESTAMP,e.YEARNUM,e.MONTHNUM,e.DAYNUM,f.PROJECTCODE,f.CAPACITY,f.PROJECTNAME from
(select n.PACTELEC,n.TIMESTAMP,n.YEARNUM,n.MONTHNUM,n.DAYNUM,n.PROJECTCODE from
(select m.*,row_number() over (partition by m.PROJECTCODE order by m.TIMESTAMP desc) rank from 
(select c.PACTELEC,c.TIMESTAMP,c.YEARNUM,c.MONTHNUM,c.DAYNUM,d.PROJECTCODE from
(select a.PACTELEC,a.TIMESTAMP,a.YEARNUM,a.MONTHNUM,a.DAYNUM,b.SUBSTATIONTYPE,B.SUBSTATIONCODE from DATA_GRIDSWITCH5MINDATA a,DEV_GRIDSWITCH b,(select trunc(sysdate) as time from dual) bb
where a.GRIDSWITCHCODE = b.GRIDSWITCHCODE and a.TIMESTAMP between bb.time and (bb.time + 1))c,DEV_BUSLINE d
where c.SUBSTATIONTYPE = d.SUBSTATIONTYPE and c.SUBSTATIONCODE = d.SUBSTATIONCODE and c.SUBSTATIONTYPE = d.SUBSTATIONTYPE)m)n
where rank = 1)e,DEV_PROJECT f
where e.PROJECTCODE = f.PROJECTCODE
)max
where min.PROJECTCODE = max.PROJECTCODE and min.PROJECTCODE = '1';


--12�汾��D_Daypower
select to_char(TIMESTAMP,'yyyy-MM-dd HH24:mi:ss') as TIMESTAMP,TO_char(TIMESTAMP,'yyyy') yearcol,TO_char(TIMESTAMP,'mm') monthcol,TO_char(TIMESTAMP,'dd') daycol, 
PACTELEC - lag(PACTELEC,1,0) over(ORDER BY TIMESTAMP asc) as D_Daypower,d.CAPACITY,
round( decode( (PACTELEC - lag(PACTELEC,1,0) over(ORDER BY TIMESTAMP asc) )/d.CAPACITY,0,0,(PACTELEC - lag(PACTELEC,1,0) over(ORDER BY TIMESTAMP asc) )/d.CAPACITY ),5) as D_Daypowerh,
lag(TIMESTAMP,1,TIMESTAMP) over(ORDER BY TIMESTAMP asc) as aheadtime
from DATA_GRIDSWITCH5MINDATA a,DEV_GRIDSWITCH b,DEV_BUSLINE c,DEV_PROJECT d,
--����ÿ��ĸ���Ŀ������ʱ���
(SELECT TO_char(TIMESTAMP,'yyyy-mm-dd') dt,max(TIMESTAMP) maxt FROM DATA_GRIDSWITCH5MINDATA a,DEV_GRIDSWITCH b,DEV_BUSLINE c,DEV_PROJECT d 
where a.GRIDSWITCHCODE = b.GRIDSWITCHCODE and b.SUBSTATIONTYPE = c.SUBSTATIONTYPE and
b.SUBSTATIONCODE = c.SUBSTATIONCODE and c.PROJECTCODE = d.PROJECTCODE and d.PROJECTCODE = '1'
GROUP BY TO_char(TIMESTAMP,'yyyy-mm-dd') ) t,
(SELECT case when max(a.TIMESTAMP) is null then to_date('1970-01-01 00:00:00','yyyy-MM-dd HH24:mi:ss') else max(a.TIMESTAMP) end as yesterdaymax FROM DATA_GRIDSWITCH5MINDATA a,DEV_GRIDSWITCH b,DEV_BUSLINE c,DEV_PROJECT d 
where a.GRIDSWITCHCODE = b.GRIDSWITCHCODE and b.SUBSTATIONTYPE = c.SUBSTATIONTYPE and 
b.SUBSTATIONCODE = c.SUBSTATIONCODE and c.PROJECTCODE = d.PROJECTCODE and d.PROJECTCODE = '1' 
and a.TIMESTAMP <  to_date(to_char(to_date('2017-03-11 23:59:00','yyyy-MM-dd HH24:mi:ss'),'yyyy-MM-dd'),'yyyy-MM-dd')
) y
where a.GRIDSWITCHCODE = b.GRIDSWITCHCODE and b.SUBSTATIONTYPE = c.SUBSTATIONTYPE and
b.SUBSTATIONCODE = c.SUBSTATIONCODE and c.PROJECTCODE = d.PROJECTCODE and d.PROJECTCODE = '1' and a.TIMESTAMP=t.maxt 
and a.TIMESTAMP >= y.yesterdaymax


--�������������Ŀ������ʱ�����������ǰ�ߵ�����ʱ��
SELECT case when max(a.TIMESTAMP) is null then to_date('1970-01-01 00:00:00','yyyy-MM-dd HH24:mi:ss') else max(a.TIMESTAMP) end as yesterdaymax FROM DATA_GRIDSWITCH5MINDATA a,DEV_GRIDSWITCH b,DEV_BUSLINE c,DEV_PROJECT d where a.GRIDSWITCHCODE = b.GRIDSWITCHCODE and 
b.SUBSTATIONTYPE = c.SUBSTATIONTYPE and b.SUBSTATIONCODE = c.SUBSTATIONCODE and c.PROJECTCODE = d.PROJECTCODE and
d.PROJECTCODE = '1' and a.TIMESTAMP <  to_date(to_char(to_date('2017-03-11 23:59:00','yyyy-MM-dd HH24:mi:ss'),'yyyy-MM-dd'),'yyyy-MM-dd'); 


--Daypower�ֶΣ���ѹվ
--10.1�汾������ѯ�ˡ�
select wmsys.wm_concat(TIMESTAMP) as TIMESTAMP,wmsys.wm_concat(PROJECTCODE) as PROJECTCODE,wmsys.wm_concat(PROJECTNAME) as PROJECTNAME,SUM(D_Daypower) as DAYPOWER,sum(CAPACITY) as CAPACITY from
(select to_char(B.TIMESTAMP,'yyyy-MM-dd HH24:mi:ss') as TIMESTAMP,B.PROJECTCODE,B.PROJECTNAME,B.PACTELEC - A.PACTELEC as D_Daypower,A.CAPACITY from 
(select e.PACTELEC,e.TIMESTAMP,e.PROJECTCODE,e.CAPACITY,f.PROJECTNAME from
(select c.PACTELEC,c.TIMESTAMP,d.PROJECTCODE,g.CAPACITY from
(select a.PACTELEC,a.TIMESTAMP,b.SUBSTATIONTYPE,B.SUBSTATIONCODE from DATA_GRIDSWITCH5MINDATA a,DEV_GRIDSWITCH b,(select trunc(sysdate) as time from dual) bb
where a.GRIDSWITCHCODE = b.GRIDSWITCHCODE and HOURNUM = '04' and MINUTENUM = '00' and a.TIMESTAMP between bb.time and (bb.time + 1))c,DEV_BUSLINE d,DEV_STATION g
where c.SUBSTATIONTYPE = d.SUBSTATIONTYPE and c.SUBSTATIONCODE = d.SUBSTATIONCODE and c.SUBSTATIONTYPE = 1 and c.SUBSTATIONCODE = g.STATIONCODE)e,DEV_PROJECT f
where e.PROJECTCODE = f.PROJECTCODE      
)A,
(select e.PACTELEC,e.TIMESTAMP,e.PROJECTCODE,f.PROJECTNAME from
(select c.PACTELEC,c.TIMESTAMP,d.PROJECTCODE from
(select a.PACTELEC,a.TIMESTAMP,b.SUBSTATIONTYPE,B.SUBSTATIONCODE from DATA_GRIDSWITCH5MINDATA a,DEV_GRIDSWITCH b,(select trunc(sysdate) as time from dual) bb
where a.GRIDSWITCHCODE = b.GRIDSWITCHCODE and HOURNUM = '23' and MINUTENUM = '00' and a.TIMESTAMP between bb.time and (bb.time + 1))c,DEV_BUSLINE d,DEV_STATION g
where c.SUBSTATIONTYPE = d.SUBSTATIONTYPE and c.SUBSTATIONCODE = d.SUBSTATIONCODE and c.SUBSTATIONTYPE = 1 and c.SUBSTATIONCODE = g.STATIONCODE)e,DEV_PROJECT f
where e.PROJECTCODE = f.PROJECTCODE
)B
where A.PROJECTCODE = B.PROJECTCODE);

--Daypower�ֶΣ�ûд�꣬�����������ôд��
--10.1�汾������ѯ�ˡ�
select wmsys.wm_concat(TIMESTAMP) as TIMESTAMP,wmsys.wm_concat(PROJECTCODE) as PROJECTCODE,wmsys.wm_concat(PROJECTNAME) as PROJECTNAME,SUM(D_value) as DAYPOWER,sum(h) as DAYPOWERH from
(select ceil(max.TIMESTAMP - min.TIMESTAMP) as DAYS,to_char(max.TIMESTAMP,'yyyy-MM-dd HH24:mi:ss') as TIMESTAMP,max.PROJECTCODE,max.PROJECTNAME,max.PACTELEC - min.PACTELEC as D_value,decode(max.CAPACITY,0,0,max.CAPACITY,(max.PACTELEC - min.PACTELEC)/max.CAPACITY) as h from 
(
select e.PACTELEC,e.TIMESTAMP,e.YEARNUM,e.MONTHNUM,e.DAYNUM,e.PROJECTCODE,f.PROJECTNAME,f.CAPACITY from
(select n.PACTELEC,n.TIMESTAMP,n.YEARNUM,n.MONTHNUM,n.DAYNUM,n.PROJECTCODE from
(select m.*,row_number() over (partition by m.PROJECTCODE order by m.TIMESTAMP desc) rank from 
(select c.PACTELEC,c.TIMESTAMP,c.YEARNUM,c.MONTHNUM,c.DAYNUM,d.PROJECTCODE from
(select a.PACTELEC,a.TIMESTAMP,a.YEARNUM,a.MONTHNUM,a.DAYNUM,b.SUBSTATIONTYPE,B.SUBSTATIONCODE from DATA_GRIDSWITCH5MINDATA a,DEV_GRIDSWITCH b,(select trunc(sysdate) as time from dual) bb
where to_date('2017-08-07 00:00:00','yyyy-MM-dd HH24:mi:ss') > a.TIMESTAMP and a.GRIDSWITCHCODE = b.GRIDSWITCHCODE)c,DEV_BUSLINE d
where c.SUBSTATIONTYPE = d.SUBSTATIONTYPE and c.SUBSTATIONCODE = d.SUBSTATIONCODE and c.SUBSTATIONTYPE = d.SUBSTATIONTYPE )m)n
where rank = 1)e,DEV_PROJECT f
where e.PROJECTCODE = f.PROJECTCODE
)min,
(select e.PACTELEC,e.TIMESTAMP,e.YEARNUM,e.MONTHNUM,e.DAYNUM,e.PROJECTCODE,f.PROJECTNAME,f.CAPACITY from
(select n.PACTELEC,n.TIMESTAMP,n.YEARNUM,n.MONTHNUM,n.DAYNUM,n.PROJECTCODE from
(select m.*,row_number() over (partition by m.PROJECTCODE order by m.TIMESTAMP desc) rank from 
(select c.PACTELEC,c.TIMESTAMP,c.YEARNUM,c.MONTHNUM,c.DAYNUM,d.PROJECTCODE from
(select a.PACTELEC,a.TIMESTAMP,a.YEARNUM,a.MONTHNUM,a.DAYNUM,b.SUBSTATIONTYPE,B.SUBSTATIONCODE from DATA_GRIDSWITCH5MINDATA a,DEV_GRIDSWITCH b,(select trunc(sysdate) as time from dual) bb
where  a.TIMESTAMP between bb.time and (bb.time + 1) and a.GRIDSWITCHCODE = b.GRIDSWITCHCODE)c,DEV_BUSLINE d
where c.SUBSTATIONTYPE = d.SUBSTATIONTYPE and c.SUBSTATIONCODE = d.SUBSTATIONCODE and c.SUBSTATIONTYPE = d.SUBSTATIONTYPE )m)n
where rank = 1)e,DEV_PROJECT f
where e.PROJECTCODE = f.PROJECTCODE
)max
where max.PROJECTCODE = min.PROJECTCODE and min.PROJECTCODE = '1')
group by PROJECTCODE;


--D_ FactoryPower������ĳ��õ��������õ�5�������ݱ�(data_ownpower5mindata���е��ֶ��й�������Actelec�����ʱ���ֵ��ȥ���쿪ʼ����ʱ���ֵ
--10.1�汾
select max.ACTELEC - min.ACTELEC as D_FactoryPower,to_char(max.TIMESTAMP,'yyyy-MM-dd HH24:mi:ss') as TIMESTAMP,max.YEARNUM,max.MONTHNUM,max.DAYNUM,max.PROJECTCODE,p.PROJECTNAME from
(select n.ACTELEC,n.TIMESTAMP,n.PROJECTCODE from
(select m.*,row_number() over (partition by m.PROJECTCODE order by m.TIMESTAMP asc) rank from 
(select a.ACTELEC,a.TIMESTAMP,d.PROJECTCODE from
DATA_OWNPOWER5MINDATA a,DEV_FACTORYELECTR c,DEV_BUSLINE d
where to_date('2010-08-07 00:00:00','yyyy-MM-dd HH24:mi:ss') > a.TIMESTAMP and a.SWITCHCODE = c.SWITCHCODE and c.SUBSTATIONCODE = d.SUBSTATIONCODE and c.SUBSTATIONTYPE = d.SUBSTATIONTYPE)m)n
where rank = 1)min,
(select n.ACTELEC,n.TIMESTAMP,n.YEARNUM,n.MONTHNUM,n.DAYNUM,n.PROJECTCODE from
(select m.*,row_number() over (partition by m.PROJECTCODE order by m.TIMESTAMP desc) rank from 
(select a.ACTELEC,a.TIMESTAMP,a.YEARNUM,a.MONTHNUM,a.DAYNUM,d.PROJECTCODE from
DATA_OWNPOWER5MINDATA a,(select trunc(sysdate) as time from dual) b,DEV_FACTORYELECTR c,DEV_BUSLINE d
where a.TIMESTAMP between b.time and (b.time + 1) and a.SWITCHCODE = c.SWITCHCODE and c.SUBSTATIONCODE = d.SUBSTATIONCODE and c.SUBSTATIONTYPE = d.SUBSTATIONTYPE)m)n
where rank = 1)max,DEV_PROJECT p
where max.PROJECTCODE = min.PROJECTCODE and min.PROJECTCODE = p.PROJECTCODE and p.PROJECTCODE= '1'
--D_ FactoryPower������ĳ��õ��������õ�5�������ݱ�(data_ownpower5mindata���е��ֶ��й�������Actelec�����ʱ���ֵ��ȥ���쿪ʼ����ʱ���ֵ
--12�汾
select TIMESTAMP,TO_char(TIMESTAMP,'yyyy') yearcol,TO_char(TIMESTAMP,'mm') monthcol,TO_char(TIMESTAMP,'dd') daycol, 
ACTELEC - lag(ACTELEC,1,0) over(ORDER BY TIMESTAMP asc) as D_FactoryPower,
lag(TIMESTAMP,1,TIMESTAMP) over(ORDER BY TIMESTAMP asc) as aheadtime
from DATA_OWNPOWER5MINDATA a,DEV_FACTORYELECTR b,DEV_BUSLINE c,DEV_PROJECT d,
--����ÿ��ĸ���Ŀ������ʱ���
(
SELECT TO_char(TIMESTAMP,'yyyy-mm-dd') dt,max(TIMESTAMP) maxt 
FROM DATA_OWNPOWER5MINDATA a,DEV_FACTORYELECTR b,DEV_BUSLINE c,DEV_PROJECT d 
where a.SWITCHCODE = b.SWITCHCODE and b.SUBSTATIONCODE = c.SUBSTATIONCODE and b.SUBSTATIONTYPE = c.SUBSTATIONTYPE and 
c.PROJECTCODE = d.PROJECTCODE and d.PROJECTCODE = '1' 
GROUP BY TO_char(TIMESTAMP,'yyyy-mm-dd') 
) t,
(
SELECT case when max(a.TIMESTAMP) is null then to_date('1970-01-01 00:00:00','yyyy-MM-dd HH24:mi:ss') else max(a.TIMESTAMP) end as yesterdaymax
FROM DATA_OWNPOWER5MINDATA a,DEV_FACTORYELECTR b,DEV_BUSLINE c,DEV_PROJECT d 
where a.SWITCHCODE = b.SWITCHCODE and b.SUBSTATIONCODE = c.SUBSTATIONCODE and b.SUBSTATIONTYPE = c.SUBSTATIONTYPE and 
c.PROJECTCODE = d.PROJECTCODE and d.PROJECTCODE = '1' 
and a.TIMESTAMP <  to_date(to_char(to_date('2017-07-28 23:59:00','yyyy-MM-dd HH24:mi:ss'),'yyyy-MM-dd'),'yyyy-MM-dd')
) y
where a.SWITCHCODE = b.SWITCHCODE and b.SUBSTATIONCODE = c.SUBSTATIONCODE and b.SUBSTATIONTYPE = c.SUBSTATIONTYPE and 
c.PROJECTCODE = d.PROJECTCODE and d.PROJECTCODE = '1' and a.TIMESTAMP=t.maxt 
and a.TIMESTAMP >= y.yesterdaymax


--D_ALLpower�ֶΣ���ѹվ
--����վ������5�������ݱ�data_GridSwitch5minData)�е��ֶ������й�������pActelec����ͣ�23��ģ�
--10.1�汾����ѯ�ˡ�
select wmsys.wm_concat(TIMESTAMP) as TIMESTAMP,wmsys.wm_concat(PROJECTCODE) as PROJECTCODE,wmsys.wm_concat(PROJECTNAME) as PROJECTNAME,SUM(PACTELEC) as D_ALLpower,SUM(CAPACITY) as CAPACITY from
(select e.PACTELEC,to_char(e.TIMESTAMP,'yyyy-MM-dd HH24:mi:ss') as TIMESTAMP,e.PROJECTCODE,e.CAPACITY,f.PROJECTNAME from
(select c.PACTELEC,c.TIMESTAMP,d.PROJECTCODE,g.CAPACITY from
(select a.PACTELEC,a.TIMESTAMP,b.SUBSTATIONTYPE,B.SUBSTATIONCODE from DATA_GRIDSWITCH5MINDATA a,DEV_GRIDSWITCH b,(select trunc(sysdate) as time from dual) bb
where a.GRIDSWITCHCODE = b.GRIDSWITCHCODE and HOURNUM = '23' and MINUTENUM = '00' and a.TIMESTAMP between bb.time and (bb.time + 1))c,DEV_BUSLINE d,DEV_STATION g
where c.SUBSTATIONTYPE = d.SUBSTATIONTYPE and c.SUBSTATIONCODE = d.SUBSTATIONCODE and c.SUBSTATIONTYPE = 1 and c.SUBSTATIONCODE = g.STATIONCODE)e,DEV_PROJECT f
where e.PROJECTCODE = f.PROJECTCODE      
);


--D_ALLInverterPower�ֶΣ�����������ĵ��շ���������Ŀ�б������5�������ݱ�(data_Inverter5minData)���ֶε��շ�������dayElec����͡�ȡ23��ġ�
--10.1�汾
--����Ϊ1�������PROJECTCODE
select g.TIMESTAMP,g.YEARNUM,g.MONTHNUM,g.DAYNUM,g.D_ALLINVERTERPOWER,g.PROJECTCODE,h.PROJECTNAME from
(select wmsys.wm_concat(TIMESTAMP) as TIMESTAMP,wmsys.wm_concat(YEARNUM) as YEARNUM,wmsys.wm_concat(MONTHNUM) as MONTHNUM,wmsys.wm_concat(DAYNUM) as DAYNUM,sum(DAYELEC) as D_ALLINVERTERPOWER,PROJECTCODE from
(
select n.TIMESTAMP,n.YEARNUM,n.MONTHNUM,n.DAYNUM,n.DAYELEC,n.PROJECTCODE from
(select m.*,row_number() over (partition by m.PROJECTCODE,to_char(m.TIMESTAMP,'yyyy-MM-dd') order by m.TIMESTAMP desc) rank from 
(select e.DAYELEC,e.TIMESTAMP,e.YEARNUM,e.MONTHNUM,e.DAYNUM,f.PROJECTCODE from
(select c.DAYELEC,c.TIMESTAMP,c.YEARNUM,c.MONTHNUM,c.DAYNUM,d.BUSLINECODE from
(select a.DAYELEC,a.TIMESTAMP,a.YEARNUM,a.MONTHNUM,a.DAYNUM,b.ONCODE from DATA_INVERTER5MINDATA a,DEV_INVERTER b
where a.TIMESTAMP >=  '2017-06-20' and a.INVERTERCODE = b.INVERTERCODE and b.TYPE = 1)c ,DEV_BOXTRANS d
where c.ONCODE = d.BOXTRANSCODE) e,DEV_BUSLINE f
where e.BUSLINECODE = f.BUSLINECODE)m)n
where n.rank =1
union all
--����Ϊ2�������
select n.TIMESTAMP,n.YEARNUM,n.MONTHNUM,n.DAYNUM,n.DAYELEC,n.PROJECTCODE from
(select m.*,row_number() over (partition by m.PROJECTCODE,to_char(m.TIMESTAMP,'yyyy-MM-dd') order by m.TIMESTAMP desc) rank from 
(select e.DAYELEC,e.TIMESTAMP,e.YEARNUM,e.MONTHNUM,e.DAYNUM,f.PROJECTCODE from
(select c.DAYELEC,c.TIMESTAMP,c.YEARNUM,c.MONTHNUM,c.DAYNUM,d.BUSLINECODE from
(select a.DAYELEC,a.TIMESTAMP,a.YEARNUM,a.MONTHNUM,a.DAYNUM,b.ONCODE from DATA_INVERTER5MINDATA a,DEV_INVERTER b
where a.TIMESTAMP >=  '2017-06-20' and a.INVERTERCODE = b.INVERTERCODE and b.TYPE = 2)c ,DEV_BOXTRANS d
where c.ONCODE = d.BOXTRANSCODE) e,DEV_BUSLINE f
where e.BUSLINECODE = f.BUSLINECODE)m)n
where n.rank =1
union all
--����Ϊ3�������
select n.TIMESTAMP,n.YEARNUM,n.MONTHNUM,n.DAYNUM,n.DAYELEC,n.PROJECTCODE from
(select m.*,row_number() over (partition by m.PROJECTCODE,to_char(m.TIMESTAMP,'yyyy-MM-dd') order by m.TIMESTAMP desc) rank from
(select e.DAYELEC,e.TIMESTAMP,e.YEARNUM,e.MONTHNUM,e.DAYNUM,f.PROJECTCODE from
(select c.DAYELEC,c.TIMESTAMP,c.YEARNUM,c.MONTHNUM,c.DAYNUM,d.BUSLINECODE from
(select a.DAYELEC,a.TIMESTAMP,a.YEARNUM,a.MONTHNUM,a.DAYNUM,b.ONCODE from DATA_INVERTER5MINDATA a,DEV_INVERTER b
where a.TIMESTAMP >= '2017-06-20' and a.INVERTERCODE = b.INVERTERCODE and b.TYPE = 3)c ,DEV_COMBINERBOX dd,DEV_BOXTRANS d
where c.ONCODE = dd.COMBINERBOXCODE and dd.TYPE = 3 and dd.ONCODE = d.BOXTRANSCODE) e,DEV_BUSLINE f
where e.BUSLINECODE = f.BUSLINECODE)m)n
where n.rank = 1)
group by PROJECTCODE,to_char(TIMESTAMP,'yyyy-MM-dd'))g, DEV_PROJECT h
where g.PROJECTCODE = h.PROJECTCODE and h.PROJECTCODE = '1';
--12�汾��˼·���������һ��վ�����е������
select A.TIMESTAMP,A.YEARNUM,A.MONTHNUM,A.DAYNUM,A.D_ALLInverterPower,B.PROJECTCODE,B.PROJECTNAME from
(select max(TIMESTAMP) as TIMESTAMP,TO_char(max(TIMESTAMP),'yyyy') yearnum,TO_char(max(TIMESTAMP),'mm') monthnum,TO_char(max(TIMESTAMP),'dd') daynum, 
sum(dayElec) as D_ALLInverterPower
from DATA_INVERTER5MINDATA a,
(
SELECT TO_char(TIMESTAMP,'yyyy-mm-dd') dt,max(TIMESTAMP) maxt ,INVERTERCODE
FROM DATA_INVERTER5MINDATA
GROUP BY INVERTERCODE,TO_char(TIMESTAMP,'yyyy-mm-dd') 
) t,
(
SELECT case when max(TIMESTAMP) is null then to_date('1970-01-01 00:00:00','yyyy-MM-dd HH24:mi:ss') else max(TIMESTAMP) end as yesterdaymax
FROM DATA_INVERTER5MINDATA
where TIMESTAMP <  to_date(to_char(to_date('2017-07-28 23:59:00','yyyy-MM-dd HH24:mi:ss'),'yyyy-MM-dd'),'yyyy-MM-dd')
) y,
(
select INVERTERCODE from(
--1
select INVERTERCODE from DEV_INVERTER a,DEV_BOXTRANS c,DEV_BUSLINE d,DEV_PROJECT e
where a.ONCODE = c.BOXTRANSCODE and a.TYPE = 1  and c.BUSLINECODE = d.BUSLINECODE
and d.PROJECTCODE = e.PROJECTCODE and e.PROJECTCODE = '1'
union all
--2
select INVERTERCODE from DEV_INVERTER a,DEV_BOXTRANS c,DEV_BUSLINE d,DEV_PROJECT e
where a.ONCODE = c.BOXTRANSCODE and a.TYPE = 2  and c.BUSLINECODE = d.BUSLINECODE
and d.PROJECTCODE = e.PROJECTCODE and e.PROJECTCODE = '1'
union all
--3
select INVERTERCODE from DEV_INVERTER a,DEV_COMBINERBOX b,DEV_BOXTRANS c,DEV_BUSLINE d,DEV_PROJECT e
where a.ONCODE = b.COMBINERBOXCODE and a.TYPE = 3 and b.TYPE = 3 and b.ONCODE = c.BOXTRANSCODE and c.BUSLINECODE = d.BUSLINECODE
and d.PROJECTCODE = e.PROJECTCODE and e.PROJECTCODE = '1')
) p
where a.TIMESTAMP=t.maxt and a.INVERTERCODE = t.INVERTERCODE and a.INVERTERCODE = p.INVERTERCODE
and a.TIMESTAMP >= y.yesterdaymax
group by TO_char(TIMESTAMP,'yyyy-mm-dd')  )A,DEV_PROJECT B
where B.PROJECTCODE = '1';


--������������Ŀ��Ӧ�����е��������code
select INVERTERCODE from
(
--1
select INVERTERCODE from DEV_INVERTER a,DEV_BOXTRANS c,DEV_BUSLINE d,DEV_PROJECT e
where a.ONCODE = c.BOXTRANSCODE and a.TYPE = 1  and c.BUSLINECODE = d.BUSLINECODE
and d.PROJECTCODE = e.PROJECTCODE and e.PROJECTCODE = '1'
union all
--2
select INVERTERCODE from DEV_INVERTER a,DEV_BOXTRANS c,DEV_BUSLINE d,DEV_PROJECT e
where a.ONCODE = c.BOXTRANSCODE and a.TYPE = 2  and c.BUSLINECODE = d.BUSLINECODE
and d.PROJECTCODE = e.PROJECTCODE and e.PROJECTCODE = '1'
union all
--3
select INVERTERCODE from DEV_INVERTER a,DEV_COMBINERBOX b,DEV_BOXTRANS c,DEV_BUSLINE d,DEV_PROJECT e
where a.ONCODE = b.COMBINERBOXCODE and a.TYPE = 3 and b.TYPE = 3 and b.ONCODE = c.BOXTRANSCODE and c.BUSLINECODE = d.BUSLINECODE
and d.PROJECTCODE = e.PROJECTCODE and e.PROJECTCODE = '1'
)




--D_AllLinePower,���м�����·���շ�����,��Ŀ�б�����·���5�������ݱ�(data_busline5minData)�е��ֶ������й�������pActelec���е���3�㵽23��Ĳ�ֵ.
select C.TIMESTAMP,C.D_AllLinePower,D.PROJECTCODE,D.PROJECTNAME,D.CAPACITY from
(select wmsys.wm_concat(TIMESTAMP) as TIMESTAMP,sum(PACTELEC) as D_AllLinePower,PROJECTCODE from
(select to_char(B.TIMESTAMP,'yyyy-MM-dd HH24:mi:ss') as TIMESTAMP,B.PACTELEC - A.PACTELEC as PACTELEC,A.PROJECTCODE from
(select a.PACTELEC,b.PROJECTCODE from DATA_BUSLINE5MINDATA a,DEV_BUSLINE b,(select trunc(sysdate) as time from dual) bb
where a.BUSLINECODE = b.BUSLINECODE and a.HOURNUM = '4' and MINUTENUM = '00' and a.TIMESTAMP between bb.time and (bb.time + 1))A,
(select a.PACTELEC,a.TIMESTAMP,b.PROJECTCODE from DATA_BUSLINE5MINDATA a,DEV_BUSLINE b,(select trunc(sysdate) as time from dual) bb
where a.BUSLINECODE = b.BUSLINECODE and a.HOURNUM = '23' and MINUTENUM = '00' and a.TIMESTAMP between bb.time and (bb.time + 1))B
where A.PROJECTCODE = B.PROJECTCODE)
group by PROJECTCODE)C,DEV_PROJECT D
where C.PROJECTCODE = D.PROJECTCODE;
--10.1�汾
select c.DAYS,c.MAXTIMESTAMP,c.MINTIMESTAMP,c.YEARNUM,c.MONTHNUM,c.DAYNUM,c.D_AllLinePower,d.PROJECTCODE,d.PROJECTNAME from
(select wmsys.wm_concat(DAYS) as DAYS,wmsys.wm_concat(MAXTIMESTAMP) as MAXTIMESTAMP,wmsys.wm_concat(MINTIMESTAMP) as MINTIMESTAMP,wmsys.wm_concat(YEARNUM) as YEARNUM,wmsys.wm_concat(MONTHNUM) as MONTHNUM,wmsys.wm_concat(DAYNUM) as DAYNUM,sum(PACTELEC) as D_AllLinePower,PROJECTCODE from
(select ceil(max.TIMESTAMP - min.TIMESTAMP) as DAYS,to_char(max.TIMESTAMP,'yyyy-MM-dd HH24:mi:ss') as MAXTIMESTAMP,to_char(min.TIMESTAMP,'yyyy-MM-dd HH24:mi:ss') AS MINTIMESTAMP,max.YEARNUM,max.MONTHNUM,max.DAYNUM,max.PACTELEC - min.PACTELEC as PACTELEC,max.PROJECTCODE from 
(select n.PACTELEC,n.TIMESTAMP,n.YEARNUM,n.MONTHNUM,n.DAYNUM,n.PROJECTCODE from
(select m.*,row_number() over (partition by m.BUSLINECODE order by m.TIMESTAMP desc) rank from
(select a.BUSLINECODE,a.PACTELEC,a.TIMESTAMP,TO_char(a.TIMESTAMP,'yyyy') YEARNUM,TO_char(a.TIMESTAMP,'mm') MONTHNUM,TO_char(a.TIMESTAMP,'dd') DAYNUM,b.PROJECTCODE from DATA_BUSLINE5MINDATA a,DEV_BUSLINE b
where to_date('2017-08-07 00:00:00','yyyy-MM-dd HH24:mi:ss') > a.TIMESTAMP and a.BUSLINECODE = b.BUSLINECODE)m)n
where rank = 1
)min,
(select n.PACTELEC,n.TIMESTAMP,n.YEARNUM,n.MONTHNUM,n.DAYNUM,n.PROJECTCODE from
(select m.*,row_number() over (partition by m.BUSLINECODE order by m.TIMESTAMP desc) rank from
(select a.BUSLINECODE,a.PACTELEC,a.TIMESTAMP,TO_char(a.TIMESTAMP,'yyyy') YEARNUM,TO_char(a.TIMESTAMP,'mm') MONTHNUM,TO_char(a.TIMESTAMP,'dd') DAYNUM,b.PROJECTCODE from DATA_BUSLINE5MINDATA a,DEV_BUSLINE b,(select trunc(sysdate) as time from dual) bb
where a.TIMESTAMP between bb.time and (bb.time + 1) and a.BUSLINECODE = b.BUSLINECODE)m)n
where rank = 1
)max
where max.PROJECTCODE = min.PROJECTCODE)
group by PROJECTCODE)c,DEV_PROJECT d
where c.PROJECTCODE = d.PROJECTCODE and d.PROJECTCODE = '1';
--�������д����͵�ǰ�ߵİ汾����ߵľ͸����ĵ���д�ģ�û����ˡ�
select f.TIMESTAMP,f.YEARNUM,f.MONTHNUM,f.DAYNUM,f.D_AllLinePower,p.PROJECTCODE,p.PROJECTNAME from
(select wmsys.wm_concat(s.TIMESTAMP) as TIMESTAMP,sum(s.PACTELEC) as D_AllLinePower,wmsys.wm_concat(s.YEARNUM) as YEARNUM,wmsys.wm_concat(s.MONTHNUM) as MONTHNUM,wmsys.wm_concat(s.DAYNUM) as DAYNUM,s.PROJECTCODE from
(select n.TIMESTAMP,n.PACTELEC,n.YEARNUM,n.MONTHNUM,n.DAYNUM,n.PROJECTCODE from
(select m.*,row_number() over (partition by m.BUSLINECODE order by m.TIMESTAMP desc) rank from
(select a.TIMESTAMP,a.BUSLINECODE,a.PACTELEC,TO_char(a.TIMESTAMP,'yyyy') YEARNUM,TO_char(a.TIMESTAMP,'mm') MONTHNUM,TO_char(a.TIMESTAMP,'dd') DAYNUM,b.PROJECTCODE from DATA_BUSLINE5MINDATA a,DEV_BUSLINE b,(select trunc(sysdate) as time from dual) bb
where a.TIMESTAMP between bb.time and (bb.time + 1) and a.BUSLINECODE = b.BUSLINECODE)m)n
where rank = 1)s
group by s.PROJECTCODE)f,DEV_PROJECT p
where f.PROJECTCODE = p.PROJECTCODE and p.PROJECTCODE = '1';
--12�汾
select TIMESTAMP,TO_char(TIMESTAMP,'yyyy') yearcol,TO_char(TIMESTAMP,'mm') monthcol,TO_char(TIMESTAMP,'dd') daycol, 
PACTELEC - lag(PACTELEC,1,0) over(ORDER BY TIMESTAMP asc) as D_AllLinePower,
lag(TIMESTAMP,1,TIMESTAMP) over(ORDER BY TIMESTAMP asc) as aheadtime
from DATA_BUSLINE5MINDATA a,DEV_BUSLINE b,DEV_PROJECT d,
--����ÿ��ĸ���Ŀ������ʱ���
(
SELECT TO_char(TIMESTAMP,'yyyy-mm-dd') dt,max(TIMESTAMP) maxt 
FROM DATA_BUSLINE5MINDATA a,DEV_BUSLINE b,DEV_PROJECT d 
where a.BUSLINECODE = b.BUSLINECODE and 
b.PROJECTCODE = d.PROJECTCODE and d.PROJECTCODE = '1' 
GROUP BY TO_char(TIMESTAMP,'yyyy-mm-dd') 
) t,
(
SELECT case when max(a.TIMESTAMP) is null then to_date('1970-01-01 00:00:00','yyyy-MM-dd HH24:mi:ss') else max(a.TIMESTAMP) end as yesterdaymax
FROM DATA_BUSLINE5MINDATA a,DEV_BUSLINE b,DEV_PROJECT d 
where a.BUSLINECODE = b.BUSLINECODE and 
b.PROJECTCODE = d.PROJECTCODE and d.PROJECTCODE = '1' 
and a.TIMESTAMP <  to_date(to_char(to_date('2017-07-28 23:59:00','yyyy-MM-dd HH24:mi:ss'),'yyyy-MM-dd'),'yyyy-MM-dd')
) y
where a.BUSLINECODE = b.BUSLINECODE and 
b.PROJECTCODE = d.PROJECTCODE and d.PROJECTCODE = '1' and a.TIMESTAMP=t.maxt 
and a.TIMESTAMP >= y.yesterdaymax;
        
        
--D_PvarrayLoss	��������������	Kwh		��վÿ��ͳ�ƣ�Stat_EveryDayData���е��ֶε������۷�������D_DayTheogen����ȥ����������ĵ��շ�������D_ALLInverterPower���Ĳ�ֵ
select AAA.D_DayTheogen - BBB.D_ALLInverterPower as D_PvarrayLoss, round(decode(BBB.CAPACITY,0,0,(AAA.D_DayTheogen - BBB.D_ALLInverterPower)/BBB.CAPACITY ),5 ) AS D_PvarrayLossh,BBB.TIMESTAMP,BBB.YEARNUM,BBB.MONTHNUM,BBB.DAYNUM,BBB.PROJECTCODE,BBB.PROJECTNAME FROM
(
select n.TIMESTAMP,TO_NUMBER(to_char(n.TIMESTAMP,'yyyy'), '9999') as YEARNUM,to_number(to_char(n.TIMESTAMP,'MM'),'99') as MONTHNUM,to_number(to_char(n.TIMESTAMP,'dd'),'99') as DAYNUM,n.D_DAYTHEOGENH,n.D_DAYTHEOGEN,n.PROJECTCODE,n.PROJECTNAME from
(select m.*,row_number() over (partition by m.PROJECTCODE,to_char(m.TIMESTAMP,'yyyy-MM-dd') order by m.TIMESTAMP desc) rank from 
(select a.TIMESTAMP,a.YEARNUM,a.MONTHNUM,a.DAYNUM,a.CANTOTALRADVAL as D_DAYTHEOGENH,a.CANTOTALRADVAL*e.CAPACITY as D_DAYTHEOGEN,d.PROJECTCODE,e.PROJECTNAME from
DATA_ENV10MINDATA a,DEV_ENVMONITOR c,DEV_BUSLINE d,DEV_PROJECT e
where a.TIMESTAMP >  to_date(to_char(to_date('2017-06-21 23:20:00','yyyy-MM-dd HH24:mi:ss'),'yyyy-MM-dd'),'yyyy-MM-dd')  and a.ENVMONITORCODE = c.ENVMONITORCODE
and c.SUBSTATIONCODE = d.SUBSTATIONCODE and c.SUBSTATIONTYPE = d.SUBSTATIONTYPE and d.PROJECTCODE = e.PROJECTCODE and d.PROJECTCODE = '1')m)n
where n.rank = 1
)AAA,
(select A.TIMESTAMP,A.YEARNUM,A.MONTHNUM,A.DAYNUM,A.D_ALLInverterPower,B.PROJECTCODE,B.PROJECTNAME,B.CAPACITY from
(select max(TIMESTAMP) as TIMESTAMP,TO_char(max(TIMESTAMP),'yyyy') yearnum,TO_char(max(TIMESTAMP),'mm') monthnum,TO_char(max(TIMESTAMP),'dd') daynum, 
sum(dayElec) as D_ALLInverterPower
from DATA_INVERTER5MINDATA a,
(
SELECT TO_char(TIMESTAMP,'yyyy-mm-dd') dt,max(TIMESTAMP) maxt ,INVERTERCODE
FROM DATA_INVERTER5MINDATA
GROUP BY INVERTERCODE,TO_char(TIMESTAMP,'yyyy-mm-dd') 
) t,
(
SELECT case when max(TIMESTAMP) is null then to_date('1970-01-01 00:00:00','yyyy-MM-dd HH24:mi:ss') else max(TIMESTAMP) end as yesterdaymax
FROM DATA_INVERTER5MINDATA
where TIMESTAMP <  to_date(to_char(to_date('2017-07-28 23:59:00','yyyy-MM-dd HH24:mi:ss'),'yyyy-MM-dd'),'yyyy-MM-dd')
) y,
(
select INVERTERCODE from(
--1
select INVERTERCODE from DEV_INVERTER a,DEV_BOXTRANS c,DEV_BUSLINE d,DEV_PROJECT e
where a.ONCODE = c.BOXTRANSCODE and a.TYPE = 1  and c.BUSLINECODE = d.BUSLINECODE
and d.PROJECTCODE = e.PROJECTCODE and e.PROJECTCODE = '1'
union all
--2
select INVERTERCODE from DEV_INVERTER a,DEV_BOXTRANS c,DEV_BUSLINE d,DEV_PROJECT e
where a.ONCODE = c.BOXTRANSCODE and a.TYPE = 2  and c.BUSLINECODE = d.BUSLINECODE
and d.PROJECTCODE = e.PROJECTCODE and e.PROJECTCODE = '1'
union all
--3
select INVERTERCODE from DEV_INVERTER a,DEV_COMBINERBOX b,DEV_BOXTRANS c,DEV_BUSLINE d,DEV_PROJECT e
where a.ONCODE = b.COMBINERBOXCODE and a.TYPE = 3 and b.TYPE = 3 and b.ONCODE = c.BOXTRANSCODE and c.BUSLINECODE = d.BUSLINECODE
and d.PROJECTCODE = e.PROJECTCODE and e.PROJECTCODE = '1')
) p
where a.TIMESTAMP=t.maxt and a.INVERTERCODE = t.INVERTERCODE and a.INVERTERCODE = p.INVERTERCODE
and a.TIMESTAMP >= y.yesterdaymax
group by TO_char(TIMESTAMP,'yyyy-mm-dd')  )A,DEV_PROJECT B
where B.PROJECTCODE = '1')BBB
where AAA.TIMESTAMP = BBB.TIMESTAMP


--D_lineBoxLoss,������·��������	,��վ����������ĵ��շ�������D_ALLInverterPower����ȥ���м�����·���շ�������D_AllLinePower���Ĳ�ֵ
select AAA.TIMESTAMP,AAA.D_ALLInverterPower - BBB.D_AllLinePower as D_lineBoxLoss,ROUND( decode(AAA.CAPACITY,0,0,(AAA.D_ALLInverterPower - BBB.D_AllLinePower)/AAA.CAPACITY),5)as D_lineBoxLossh
AAA.YEARNUM,AAA.MONTHNUM,AAA.DAYNUM,AAA.PROJECTCODE,AAA.PROJECTNAME FROM
(select A.TIMESTAMP,A.YEARNUM,A.MONTHNUM,A.DAYNUM,A.D_ALLInverterPower,B.PROJECTCODE,B.PROJECTNAME,B.CAPACITY from
(select max(TIMESTAMP) as TIMESTAMP,TO_char(max(TIMESTAMP),'yyyy') yearnum,TO_char(max(TIMESTAMP),'mm') monthnum,TO_char(max(TIMESTAMP),'dd') daynum, 
sum(dayElec) as D_ALLInverterPower
from DATA_INVERTER5MINDATA a,
(
SELECT TO_char(TIMESTAMP,'yyyy-mm-dd') dt,max(TIMESTAMP) maxt ,INVERTERCODE
FROM DATA_INVERTER5MINDATA
GROUP BY INVERTERCODE,TO_char(TIMESTAMP,'yyyy-mm-dd') 
) t,
(
SELECT case when max(TIMESTAMP) is null then to_date('1970-01-01 00:00:00','yyyy-MM-dd HH24:mi:ss') else max(TIMESTAMP) end as yesterdaymax
FROM DATA_INVERTER5MINDATA
where TIMESTAMP <  to_date(to_char(to_date('2017-07-28 23:59:00','yyyy-MM-dd HH24:mi:ss'),'yyyy-MM-dd'),'yyyy-MM-dd')
) y,
(
select INVERTERCODE from(
--1
select INVERTERCODE from DEV_INVERTER a,DEV_BOXTRANS c,DEV_BUSLINE d,DEV_PROJECT e
where a.ONCODE = c.BOXTRANSCODE and a.TYPE = 1  and c.BUSLINECODE = d.BUSLINECODE
and d.PROJECTCODE = e.PROJECTCODE and e.PROJECTCODE = '1'
union all
--2
select INVERTERCODE from DEV_INVERTER a,DEV_BOXTRANS c,DEV_BUSLINE d,DEV_PROJECT e
where a.ONCODE = c.BOXTRANSCODE and a.TYPE = 2  and c.BUSLINECODE = d.BUSLINECODE
and d.PROJECTCODE = e.PROJECTCODE and e.PROJECTCODE = '1'
union all
--3
select INVERTERCODE from DEV_INVERTER a,DEV_COMBINERBOX b,DEV_BOXTRANS c,DEV_BUSLINE d,DEV_PROJECT e
where a.ONCODE = b.COMBINERBOXCODE and a.TYPE = 3 and b.TYPE = 3 and b.ONCODE = c.BOXTRANSCODE and c.BUSLINECODE = d.BUSLINECODE
and d.PROJECTCODE = e.PROJECTCODE and e.PROJECTCODE = '1')
) p
where a.TIMESTAMP=t.maxt and a.INVERTERCODE = t.INVERTERCODE and a.INVERTERCODE = p.INVERTERCODE
and a.TIMESTAMP >= y.yesterdaymax
group by TO_char(TIMESTAMP,'yyyy-mm-dd')  )A,DEV_PROJECT B
where B.PROJECTCODE = '1')AAA,
(
select TIMESTAMP,TO_char(TIMESTAMP,'yyyy') yearnum,TO_char(TIMESTAMP,'mm') monthnum,TO_char(TIMESTAMP,'dd') daynum, 
PACTELEC - lag(PACTELEC,1,0) over(ORDER BY TIMESTAMP asc) as D_AllLinePower,
lag(TIMESTAMP,1,TIMESTAMP) over(ORDER BY TIMESTAMP asc) as aheadtime
from DATA_BUSLINE5MINDATA a,DEV_BUSLINE b,DEV_PROJECT d,
--����ÿ��ĸ���Ŀ������ʱ���
(
SELECT TO_char(TIMESTAMP,'yyyy-mm-dd') dt,max(TIMESTAMP) maxt 
FROM DATA_BUSLINE5MINDATA a,DEV_BUSLINE b,DEV_PROJECT d 
where a.BUSLINECODE = b.BUSLINECODE and 
b.PROJECTCODE = d.PROJECTCODE and d.PROJECTCODE = '1' 
GROUP BY TO_char(TIMESTAMP,'yyyy-mm-dd') 
) t,
(
SELECT case when max(a.TIMESTAMP) is null then to_date('1970-01-01 00:00:00','yyyy-MM-dd HH24:mi:ss') else max(a.TIMESTAMP) end as yesterdaymax
FROM DATA_BUSLINE5MINDATA a,DEV_BUSLINE b,DEV_PROJECT d 
where a.BUSLINECODE = b.BUSLINECODE and 
b.PROJECTCODE = d.PROJECTCODE and d.PROJECTCODE = '1' 
and a.TIMESTAMP <  to_date(to_char(to_date('2017-07-28 23:59:00','yyyy-MM-dd HH24:mi:ss'),'yyyy-MM-dd'),'yyyy-MM-dd')
) y
where a.BUSLINECODE = b.BUSLINECODE and 
b.PROJECTCODE = d.PROJECTCODE and d.PROJECTCODE = '1' and a.TIMESTAMP=t.maxt 
and a.TIMESTAMP >= y.yesterdaymax
)BBB
where AAA.TIMESTAMP = BBB.TIMESTAMP
--D_StationLoss	��ѹվ���,��վ���м�����·���շ�������D_AllLinePower����ȥ�ֶε���ʵ������������D_Daypower���Ĳ�ֵ
select AAA.TIMESTAMP,TO_char(AAA.TIMESTAMP,'yyyy') yearnum,TO_char(AAA.TIMESTAMP,'mm') monthnum,TO_char(AAA.TIMESTAMP,'dd') daynum,
AAA.D_AllLinePower - BBB.D_Daypower AS D_StationLoss,round( decode(BBB.CAPACITY,0,0,(AAA.D_AllLinePower - BBB.D_Daypower)/BBB.CAPACITY),5 ) as D_StationLossh from
(
select TIMESTAMP,TO_char(TIMESTAMP,'yyyy') yearnum,TO_char(TIMESTAMP,'mm') monthnum,TO_char(TIMESTAMP,'dd') daynum, 
PACTELEC - lag(PACTELEC,1,0) over(ORDER BY TIMESTAMP asc) as D_AllLinePower,
lag(TIMESTAMP,1,TIMESTAMP) over(ORDER BY TIMESTAMP asc) as aheadtime
from DATA_BUSLINE5MINDATA a,DEV_BUSLINE b,DEV_PROJECT d,
(
SELECT TO_char(TIMESTAMP,'yyyy-mm-dd') dt,max(TIMESTAMP) maxt 
FROM DATA_BUSLINE5MINDATA a,DEV_BUSLINE b,DEV_PROJECT d 
where a.BUSLINECODE = b.BUSLINECODE and 
b.PROJECTCODE = d.PROJECTCODE and d.PROJECTCODE = '1' 
GROUP BY TO_char(TIMESTAMP,'yyyy-mm-dd') 
) t,
(
SELECT case when max(a.TIMESTAMP) is null then to_date('1970-01-01 00:00:00','yyyy-MM-dd HH24:mi:ss') else max(a.TIMESTAMP) end as yesterdaymax
FROM DATA_BUSLINE5MINDATA a,DEV_BUSLINE b,DEV_PROJECT d 
where a.BUSLINECODE = b.BUSLINECODE and 
b.PROJECTCODE = d.PROJECTCODE and d.PROJECTCODE = '1' 
and a.TIMESTAMP <  to_date(to_char(to_date('2017-07-28 23:59:00','yyyy-MM-dd HH24:mi:ss'),'yyyy-MM-dd'),'yyyy-MM-dd')
) y
where a.BUSLINECODE = b.BUSLINECODE and 
b.PROJECTCODE = d.PROJECTCODE and d.PROJECTCODE = '1' and a.TIMESTAMP=t.maxt 
and a.TIMESTAMP >= y.yesterdaymax
)AAA,
(
select TIMESTAMP,TO_char(TIMESTAMP,'yyyy') yearcol,TO_char(TIMESTAMP,'mm') monthcol,TO_char(TIMESTAMP,'dd') daycol, 
PACTELEC - lag(PACTELEC,1,0) over(ORDER BY TIMESTAMP asc) as D_Daypower,d.CAPACITY,
round(decode( (PACTELEC - lag(PACTELEC,1,0) over(ORDER BY TIMESTAMP asc) )/d.CAPACITY,0,0,(PACTELEC - lag(PACTELEC,1,0) over(ORDER BY TIMESTAMP asc) )/d.CAPACITY ),5) as D_Daypowerh,
lag(TIMESTAMP,1,TIMESTAMP) over(ORDER BY TIMESTAMP asc) as aheadtime
from DATA_GRIDSWITCH5MINDATA a,DEV_GRIDSWITCH b,DEV_BUSLINE c,DEV_PROJECT d,
(SELECT TO_char(TIMESTAMP,'yyyy-mm-dd') dt,max(TIMESTAMP) maxt FROM DATA_GRIDSWITCH5MINDATA a,DEV_GRIDSWITCH b,DEV_BUSLINE c,DEV_PROJECT d 
where a.GRIDSWITCHCODE = b.GRIDSWITCHCODE and b.SUBSTATIONTYPE = c.SUBSTATIONTYPE and
b.SUBSTATIONCODE = c.SUBSTATIONCODE and c.PROJECTCODE = d.PROJECTCODE and d.PROJECTCODE = '1'
GROUP BY TO_char(TIMESTAMP,'yyyy-mm-dd') ) t,
(SELECT case when max(a.TIMESTAMP) is null then to_date('1970-01-01 00:00:00','yyyy-MM-dd HH24:mi:ss') else max(a.TIMESTAMP) end as yesterdaymax FROM DATA_GRIDSWITCH5MINDATA a,DEV_GRIDSWITCH b,DEV_BUSLINE c,DEV_PROJECT d 
where a.GRIDSWITCHCODE = b.GRIDSWITCHCODE and b.SUBSTATIONTYPE = c.SUBSTATIONTYPE and 
b.SUBSTATIONCODE = c.SUBSTATIONCODE and c.PROJECTCODE = d.PROJECTCODE and d.PROJECTCODE = '1' 
and a.TIMESTAMP <  to_date(to_char(to_date('2017-03-11 23:59:00','yyyy-MM-dd HH24:mi:ss'),'yyyy-MM-dd'),'yyyy-MM-dd')
) y
where a.GRIDSWITCHCODE = b.GRIDSWITCHCODE and b.SUBSTATIONTYPE = c.SUBSTATIONTYPE and
b.SUBSTATIONCODE = c.SUBSTATIONCODE and c.PROJECTCODE = d.PROJECTCODE and d.PROJECTCODE = '1' and a.TIMESTAMP=t.maxt 
and a.TIMESTAMP >= y.yesterdaymax
)BBB
where AAA.TIMESTAMP = BBB.TIMESTAMP





--¬�ܷ�������һ��sql
SELECT dt,TO_char(TIMESTAMP,'yyyy') yearcol,TO_char(TIMESTAMP,'mm') monthcol,TO_char(TIMESTAMP,'dd') daycol,TIMESTAMP,
ac_v1-lag(ac_v1,1,0) over(ORDER BY TIMESTAMP asc) VAL,ac_v1,
lag(TIMESTAMP,1,sysdate) over(ORDER BY TIMESTAMP asc) FROM 
DATA_INVERTER5MINDATA d INNER join
(SELECT TO_char(TIMESTAMP,'yyyy-mm-dd') dt,max(TIMESTAMP) maxt FROM DATA_INVERTER5MINDATA GROUP BY TO_char(TIMESTAMP,'yyyy-mm-dd') ) t 
ON d.TIMESTAMP=t.maxt







