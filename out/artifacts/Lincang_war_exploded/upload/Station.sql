--�����������˵��ȫ��ȡ���ʱ������ݣ�Ȼ��ʵʱ���ǡ�
--��վˮƽ���ܷ���	Z_Thoriradi,���û�������豸��(dev_EnvMonitor)��������Դ����10�������ݱ�(data_Env10minData)�е���Ӧ��ƽ��ֵ��gradavg��
--��վ��б���ܷ���	Z_Tincliradi,���û�������豸��(dev_EnvMonitor)��������Դ����10�������ݱ�(data_Env10minData)�е���Ӧ��ƽ��ֵ��Canradavg��
--��վֱ��Z_Direct,���û�������豸��(dev_EnvMonitor)��������Դ����10�������ݱ�(data_Env10minData)�е���Ӧ��ƽ��ֵ��dradavg��
--��վɢ��Z_Dispersion,���û�������豸��(dev_EnvMonitor)��������Դ����10�������ݱ�(data_Env10minData)�е���Ӧ��ƽ��ֵ��Sradavg��
--��վ����ʱ��Z_Dlighth,���û�������豸��(dev_EnvMonitor)��������Դ����10�������ݱ�(data_Env10minData)�е�����ʱ����TOTALRADHOUR��
--��վ�¶�Z_Temp,���û�������豸��(dev_EnvMonitor) ��������Դ����10�������ݱ�(data_Env10minData)�е��ֶΣ��¶�10����ƽ��ֵ��Tempavg��
--��վ����Z_Wind,���û�������豸��(dev_EnvMonitor) ��������Դ����10�������ݱ�(data_Env10minData)�е��ֶΣ�����10����ƽ��ֵ��Winspavg��
select n.GRADAVG as Z_Thoriradi,n.CANRADAVG as Z_Tincliradi,n.DRADAVG as Z_Direct,n.SRADAVG as Z_Dispersion,n.TOTALRADHOUR as Z_Dlighth,n.TEMPAVG as Z_Temp,n.WINSPAVG as Z_Wind,to_char(n.TIMESTAMP,'yyyy-MM-dd HH24:mi:ss') as TIMESTAMP,n.PROJECTCODE,e.PROJECTNAME from
(select m.*,row_number() over (partition by m.PROJECTCODE order by m.TIMESTAMP desc) rank from 
(select a.GRADAVG,a.CANRADAVG,a.DRADAVG,a.SRADAVG,a.TOTALRADHOUR,a.TEMPAVG,a.WINSPAVG,a.TIMESTAMP,d.PROJECTCODE from
DATA_ENV10MINDATA a,(select trunc(sysdate) as time from dual) b,DEV_ENVMONITOR c,DEV_BUSLINE d
where a.TIMESTAMP between b.time and (b.time + 1) and a.ENVMONITORCODE = c.ENVMONITORCODE and
c.SUBSTATIONCODE = d.SUBSTATIONCODE and c.SUBSTATIONTYPE = d.SUBSTATIONTYPE)m)n,DEV_PROJECT e
where rank = 1 and n.PROJECTCODE = e.PROJECTCODE;


--��վ�ܷ������ۼ�ֵ,Z_YearTOTALRADVAL,���û�������豸��(dev_EnvMonitor) ��������Դ����10�������ݱ�(data_Env10minData)�е��ֶΣ��ܷ��䵱�����ۼ�ֵ��TOTALRADVAL����������ֵ���
--ȥȡʱ�������Ǹ������ʱ����ƴ��ƴ�ġ����ʱ�䲻ȥ�ˣ���ʹ���ϱߵ�ʱ��
select g.TIMESTAMP,g.Z_YearTOTALRADVA,h.PROJECTCODE,h.PROJECTNAME from
(select wmsys.wm_concat(f.TIMESTAMP) as TIMESTAMP,sum(TOTALRADVAL) as Z_YearTOTALRADVA, f.PROJECTCODE from
(select to_char(n.TIMESTAMP,'yyyy-MM-dd HH24:mi:ss') as TIMESTAMP,n.TOTALRADVAL,n.PROJECTCODE from
(select m.*,row_number() over (partition by to_char(m.TIMESTAMP,'yyyy-mm-dd') order by TIMESTAMP desc) rank from 
(select a.TIMESTAMP,a.TOTALRADVAL,d.PROJECTCODE from
DATA_ENV10MINDATA a,(select trunc(sysdate) as time from dual) b,DEV_ENVMONITOR c,DEV_BUSLINE d
where to_char(a.TIMESTAMP,'yyyy') = to_char(b.time,'yyyy') and a.ENVMONITORCODE = c.ENVMONITORCODE and
c.SUBSTATIONCODE = d.SUBSTATIONCODE and c.SUBSTATIONTYPE = d.SUBSTATIONTYPE)m)n
where rank = 1)f
group by f.PROJECTCODE)g,DEV_PROJECT h
where g.PROJECTCODE = h.PROJECTCODE;


--��վʵʱ�й�����	D_pwr,������5�������ݱ�(data_GridSwitch5minData)�е��ֶ��й����ʣ�pwr��
select e.TIMESTAMP,e.DPWR,f.PROJECTCODE,f.PROJECTNAME from
(select to_char(n.TIMESTAMP,'yyyy-MM-dd HH24:mi:ss') as TIMESTAMP,n.PWR as DPWR,n.PROJECTCODE from
(select m.*,row_number() over (partition by m.PROJECTCODE order by m.TIMESTAMP desc) rank from 
(select TIMESTAMP,a.PWR,d.PROJECTCODE from
DATA_GRIDSWITCH5MINDATA a,(select trunc(sysdate) as time from dual) b,DEV_GRIDSWITCH c,DEV_BUSLINE d
where a.TIMESTAMP between b.time and (b.time + 1) and a.GRIDSWITCHCODE = c.GRIDSWITCHCODE and
c.SUBSTATIONTYPE = d.SUBSTATIONTYPE and c.SUBSTATIONCODE = d.SUBSTATIONCODE)m)n
where n.rank = 1)e,DEV_PROJECT f
where e.PROJECTCODE = f.PROJECTCODE;

--��վ����ʵʱ���۷�������D_Theogen	������Դ����10�������ݱ�(data_Env10minData)�е���Ӧ��б����䵱�����ۼ�ֵ��CANTOTALRADVAL����3�㵽ͳ��ʱ���Ĳ�ֵ����װ������
--��վ����ʵʱ���۷���Сʱ��,D_Theogenh,����Դ����10�������ݱ�(data_Env10minData)�е���Ӧ��б����䵱�����ۼ�ֵ��CANTOTALRADVAL����3�㵽ͳ��ʱ���Ĳ�ֵ
select max.TIMESTAMP,(max.CANTOTALRADVAL - min.CANTOTALRADVAL)*max.CAPACITY as DTheogen,max.CANTOTALRADVAL - min.CANTOTALRADVAL as DTheogenh,max.PROJECTCODE,max.PROJECTNAME from
(select n.CANTOTALRADVAL,to_char(n.TIMESTAMP,'yyyy-MM-dd HH24:mi:ss') as TIMESTAMPP,n.PROJECTCODE,e.PROJECTNAME,e.CAPACITY from
(select m.*,row_number() over (partition by m.PROJECTCODE order by m.TIMESTAMP asc) rank from 
(select a.CANTOTALRADVAL,a.TIMESTAMP,d.PROJECTCODE from
DATA_ENV10MINDATA a,(select trunc(sysdate) as time from dual) b,DEV_ENVMONITOR c,DEV_BUSLINE d
where a.TIMESTAMP between b.time and (b.time + 1) and
a.ENVMONITORCODE = c.ENVMONITORCODE and c.SUBSTATIONCODE = d.SUBSTATIONCODE and c.SUBSTATIONTYPE = d.SUBSTATIONTYPE)m)n,DEV_PROJECT e
where rank = 1 and n.PROJECTCODE = e.PROJECTCODE
)min,
(select n.CANTOTALRADVAL,to_char(n.TIMESTAMP,'yyyy-MM-dd HH24:mi:ss') as TIMESTAMP,n.PROJECTCODE,e.PROJECTNAME,e.CAPACITY from
(select m.*,row_number() over (partition by m.PROJECTCODE order by m.TIMESTAMP desc) rank from 
(select a.CANTOTALRADVAL,a.TIMESTAMP,d.PROJECTCODE from
DATA_ENV10MINDATA a,(select trunc(sysdate) as time from dual) b,DEV_ENVMONITOR c,DEV_BUSLINE d
where a.TIMESTAMP between b.time and (b.time + 1) and
a.ENVMONITORCODE = c.ENVMONITORCODE and c.SUBSTATIONCODE = d.SUBSTATIONCODE and c.SUBSTATIONTYPE = d.SUBSTATIONTYPE)m)n,DEV_PROJECT e
where rank = 1 and n.PROJECTCODE = e.PROJECTCODE
)max
where max.PROJECTCODE = min.PROJECTCODE;

--��վ����ʵʱ��������	D_Amountpower��������5�������ݱ�data_GridSwitch5minData)�е��ֶ������й�������pActelec����4�㵽ͳ��ʱ���Ĳ�ֵ
--��վ����ʵʱ����������ЧСʱ��	D_Amountpowerh��������5�������ݱ�data_GridSwitch5minData)�е��ֶ������й�������pActelec����3�㵽ͳ��ʱ���Ĳ�ֵ
--���Ը�վ��װ������
select to_char(max.TIMESTAMP,'yyyy-MM-dd HH24:mi:ss') as TIMESTAMP,max.PACTELEC - min.PACTELEC as DAmountpower,decode(max.CAPACITY,0,0,max.CAPACITY,(max.PACTELEC - min.PACTELEC)/max.CAPACITY) as DAmountpowerh,max.PROJECTCODE,max.PROJECTNAME from
(select e.TIMESTAMP,e.PACTELEC,f.PROJECTCODE,f.PROJECTNAME from
(select n.TIMESTAMP,n.PACTELEC,n.PROJECTCODE from
(select m.*,row_number() over (partition by m.PROJECTCODE order by m.TIMESTAMP asc) rank from 
(select a.TIMESTAMP,a.PACTELEC,d.PROJECTCODE from
DATA_GRIDSWITCH5MINDATA a,(select trunc(sysdate) as time from dual) b,DEV_GRIDSWITCH c,DEV_BUSLINE d
where a.TIMESTAMP between b.time and (b.time + 1) and a.GRIDSWITCHCODE = c.GRIDSWITCHCODE and 
c.SUBSTATIONTYPE = d.SUBSTATIONTYPE and c.SUBSTATIONCODE = d.SUBSTATIONCODE)m)n
where n.rank = 1)e,DEV_PROJECT f
where e.PROJECTCODE = f.PROJECTCODE)min,
(select e.TIMESTAMP,e.PACTELEC,f.PROJECTCODE,f.PROJECTNAME,f.CAPACITY from
(select n.TIMESTAMP,n.PACTELEC,n.PROJECTCODE from
(select m.*,row_number() over (partition by m.PROJECTCODE order by m.TIMESTAMP desc) rank from 
(select a.TIMESTAMP,a.PACTELEC,d.PROJECTCODE from
DATA_GRIDSWITCH5MINDATA a,(select trunc(sysdate) as time from dual) b,DEV_GRIDSWITCH c,DEV_BUSLINE d
where a.TIMESTAMP between b.time and (b.time + 1) and a.GRIDSWITCHCODE = c.GRIDSWITCHCODE and
c.SUBSTATIONTYPE = d.SUBSTATIONTYPE and c.SUBSTATIONCODE = d.SUBSTATIONCODE)m)n
where n.rank = 1)e,DEV_PROJECT f
where e.PROJECTCODE = f.PROJECTCODE)max
where max.PROJECTCODE = min.PROJECTCODE;


--��վ����ʵʱ��������/���۷�����������D_Genratio	����������(Amountpower)�������۷�����(Theogen)
select A.TIMESTAMP,decode(B.DTheogen,0,0,A.DAmountpower/B.DTheogen) as DGenratio,A.PROJECTCODE,A.PROJECTNAME from
(select to_char(max.TIMESTAMP,'yyyy-MM-dd HH24:mi:ss') as TIMESTAMP,max.PACTELEC - min.PACTELEC as DAmountpower,decode(max.CAPACITY,0,0,max.CAPACITY,(max.PACTELEC - min.PACTELEC)/max.CAPACITY) as DAmountpowerh,max.PROJECTCODE,max.PROJECTNAME from
(select e.TIMESTAMP,e.PACTELEC,f.PROJECTCODE,f.PROJECTNAME from
(select n.TIMESTAMP,n.PACTELEC,n.PROJECTCODE from
(select m.*,row_number() over (partition by m.PROJECTCODE order by m.TIMESTAMP asc) rank from 
(select a.TIMESTAMP,a.PACTELEC,d.PROJECTCODE from
DATA_GRIDSWITCH5MINDATA a,(select trunc(sysdate) as time from dual) b,DEV_GRIDSWITCH c,DEV_BUSLINE d
where a.TIMESTAMP between b.time and (b.time + 1) and a.GRIDSWITCHCODE = c.GRIDSWITCHCODE and 
c.SUBSTATIONTYPE = d.SUBSTATIONTYPE and c.SUBSTATIONCODE = d.SUBSTATIONCODE)m)n
where n.rank = 1)e,DEV_PROJECT f
where e.PROJECTCODE = f.PROJECTCODE)min,
(select e.TIMESTAMP,e.PACTELEC,f.PROJECTCODE,f.PROJECTNAME,f.CAPACITY from
(select n.TIMESTAMP,n.PACTELEC,n.PROJECTCODE from
(select m.*,row_number() over (partition by m.PROJECTCODE order by m.TIMESTAMP desc) rank from 
(select a.TIMESTAMP,a.PACTELEC,d.PROJECTCODE from
DATA_GRIDSWITCH5MINDATA a,(select trunc(sysdate) as time from dual) b,DEV_GRIDSWITCH c,DEV_BUSLINE d
where a.TIMESTAMP between b.time and (b.time + 1) and a.GRIDSWITCHCODE = c.GRIDSWITCHCODE and
c.SUBSTATIONTYPE = d.SUBSTATIONTYPE and c.SUBSTATIONCODE = d.SUBSTATIONCODE)m)n
where n.rank = 1)e,DEV_PROJECT f
where e.PROJECTCODE = f.PROJECTCODE)max
where max.PROJECTCODE = min.PROJECTCODE)A,
(select max.TIMESTAMP,(max.CANTOTALRADVAL - min.CANTOTALRADVAL)*max.CAPACITY as DTheogen,max.CANTOTALRADVAL - min.CANTOTALRADVAL as DTheogenh,max.PROJECTCODE,max.PROJECTNAME from
(select n.CANTOTALRADVAL,to_char(n.TIMESTAMP,'yyyy-MM-dd HH24:mi:ss') as TIMESTAMPP,n.PROJECTCODE,e.PROJECTNAME,e.CAPACITY from
(select m.*,row_number() over (partition by m.PROJECTCODE order by m.TIMESTAMP asc) rank from 
(select a.CANTOTALRADVAL,a.TIMESTAMP,d.PROJECTCODE from
DATA_ENV10MINDATA a,(select trunc(sysdate) as time from dual) b,DEV_ENVMONITOR c,DEV_BUSLINE d
where a.TIMESTAMP between b.time and (b.time + 1) and
a.ENVMONITORCODE = c.ENVMONITORCODE and c.SUBSTATIONCODE = d.SUBSTATIONCODE and c.SUBSTATIONTYPE = d.SUBSTATIONTYPE)m)n,DEV_PROJECT e
where rank = 1 and n.PROJECTCODE = e.PROJECTCODE
)min,
(select n.CANTOTALRADVAL,to_char(n.TIMESTAMP,'yyyy-MM-dd HH24:mi:ss') as TIMESTAMP,n.PROJECTCODE,e.PROJECTNAME,e.CAPACITY from
(select m.*,row_number() over (partition by m.PROJECTCODE order by m.TIMESTAMP desc) rank from 
(select a.CANTOTALRADVAL,a.TIMESTAMP,d.PROJECTCODE from
DATA_ENV10MINDATA a,(select trunc(sysdate) as time from dual) b,DEV_ENVMONITOR c,DEV_BUSLINE d
where a.TIMESTAMP between b.time and (b.time + 1) and
a.ENVMONITORCODE = c.ENVMONITORCODE and c.SUBSTATIONCODE = d.SUBSTATIONCODE and c.SUBSTATIONTYPE = d.SUBSTATIONTYPE)m)n,DEV_PROJECT e
where rank = 1 and n.PROJECTCODE = e.PROJECTCODE
)max
where max.PROJECTCODE = min.PROJECTCODE)B
where A.TIMESTAMP = B.TIMESTAMP and A.PROJECTCODE = B.PROJECTCODE





--��վ�ۼƼ��ſ�����ֲ��,D_tree	Number(10),	������5�������ݱ�data_GridSwitch5minData)�е��ֶ������й�������pActelec������20.7�ٳ���10000(ע:20.7�ȵ��1����)
select to_char(e.TIMESTAMP,'yyyy-MM-dd HH24:mi:ss') as TIMESTAMP,e.PACTELEC,f.PROJECTCODE,f.PROJECTNAME,f.CAPACITY from
(select n.TIMESTAMP,n.PACTELEC,n.PROJECTCODE from
(select m.*,row_number() over (partition by m.PROJECTCODE order by m.TIMESTAMP desc) rank from 
(select a.TIMESTAMP,a.PACTELEC,d.PROJECTCODE from
DATA_GRIDSWITCH5MINDATA a,(select trunc(sysdate) as time from dual) b,DEV_GRIDSWITCH c,DEV_BUSLINE d
where a.TIMESTAMP between b.time and (b.time + 1) and a.GRIDSWITCHCODE = c.GRIDSWITCHCODE and
c.SUBSTATIONTYPE = d.SUBSTATIONTYPE and c.SUBSTATIONCODE = d.SUBSTATIONCODE)m)n
where n.rank = 1)e,DEV_PROJECT f
where e.PROJECTCODE = f.PROJECTCODE







--����վ�ۼ�ʵ����������,D_power,����վ������5�������ݱ�data_GridSwitch5minData)�е��ֶ������й�������pActelec�����
--����վ�ۼ�ʵ������������ЧСʱ��,D_powerh,����վ������5�������ݱ�data_GridSwitch5minData)�е��ֶ������й�������pActelec�����Ը�վ��װ���������
--��������һ�����⣬����վ֮����Ŀ��֪����ʲô��
--��10.1�汾֮��˵������վ��������������Ҫд�ˡ�
select sum(PACTELEC)as D_power,sum(CAPACITY) as CAPACITYS from 
(select e.TIMESTAMP,e.PACTELEC,f.PROJECTCODE,f.PROJECTNAME,f.CAPACITY from
(select to_char(n.TIMESTAMP,'yyyy-MM-dd HH24:mi:ss') as TIMESTAMP,n.PACTELEC,n.PROJECTCODE from
(select m.*,row_number() over (partition by m.PROJECTCODE order by m.TIMESTAMP desc) rank from 
(select a.TIMESTAMP,a.PACTELEC,d.PROJECTCODE from
DATA_GRIDSWITCH5MINDATA a,(select trunc(sysdate) as time from dual) b,DEV_GRIDSWITCH c,DEV_BUSLINE d
where a.TIMESTAMP between b.time and (b.time + 1) and a.GRIDSWITCHCODE = c.GRIDSWITCHCODE and
c.SUBSTATIONTYPE = d.SUBSTATIONTYPE and c.SUBSTATIONCODE = d.SUBSTATIONCODE)m)n
where n.rank = 1)e,DEV_PROJECT f
where e.PROJECTCODE = f.PROJECTCODE)