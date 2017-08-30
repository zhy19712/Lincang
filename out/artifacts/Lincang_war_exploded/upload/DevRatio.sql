--S_Inv_RateAve��������������ʵ�ƽ��ֵ��x_iֵΪ�����5�������ݱ�(data_Inverter5minData)��ͬ�ͺŵ��ֶν������й����ʣ�ac_pwr����ֵ��
--NΪ�����ͳ�� (Stat_InverterData)�е��ֶ���ͬ�ͺ������������InverterNum��������ֵ���������ƽ��ֵ��

--����Ǧ̵��㷨
select h.times,h.avg,h.STDDEV,h.MODEL,k.PROJECTCODE,k.PROJECTNAME from
(
select WMSYS.WM_CONCAT(TIMESTAMP) as times,avg(AC_PWR) as avg,STDDEV_POP(AC_PWR) as STDDEV,MODEL,PROJECTCODE from 
--���������
(select s.TIMESTAMP,s.AC_PWR,d.MODEL,f.PROJECTCODE from
(select n.TIMESTAMP,n.AC_PWR,n.INVERTERCODE from
(select m.*,row_number() over (partition by m.INVERTERCODE order by m.TIMESTAMP desc) rank from 
(select to_char(a.TIMESTAMP,'yyyy-MM-dd HH24:mi:ss') as TIMESTAMP,a.AC_PWR,a.INVERTERCODE from
DATA_INVERTER5MINDATA a,(select trunc(sysdate) as time from dual) b
where a.TIMESTAMP between b.time and (b.time + 1) )m)n
where rank = 1)s,DEV_INVERTER c,DEV_INVERTERMODEL d,DEV_BOXTRANS e,DEV_BUSLINE f
where s.INVERTERCODE = c.INVERTERCODE and c.MODELID = d.MODELID and c.TYPE = 1 and c.ONCODE = e.BOXTRANSCODE and e.BUSLINECODE = f.BUSLINECODE
union all
--��ɢ�����
select s.TIMESTAMP,s.AC_PWR,d.MODEL,f.PROJECTCODE from
(select n.TIMESTAMP,n.AC_PWR,n.INVERTERCODE from
(select m.*,row_number() over (partition by m.INVERTERCODE order by m.TIMESTAMP desc) rank from 
(select to_char(a.TIMESTAMP,'yyyy-MM-dd HH24:mi:ss') as TIMESTAMP,a.AC_PWR,a.INVERTERCODE from
DATA_INVERTER5MINDATA a,(select trunc(sysdate) as time from dual) b
where a.TIMESTAMP between b.time and (b.time + 1) )m)n
where rank = 1)s,DEV_INVERTER c,DEV_INVERTERMODEL d,DEV_BOXTRANS e,DEV_BUSLINE f
where s.INVERTERCODE = c.INVERTERCODE and c.MODELID = d.MODELID and c.TYPE = 2 and c.ONCODE = e.BOXTRANSCODE and e.BUSLINECODE = f.BUSLINECODE
union all
--�鴮�����
select s.TIMESTAMP,s.AC_PWR,d.MODEL,f.PROJECTCODE from
(select n.TIMESTAMP,n.AC_PWR,n.INVERTERCODE from
(select m.*,row_number() over (partition by m.INVERTERCODE order by m.TIMESTAMP desc) rank from 
(select to_char(a.TIMESTAMP,'yyyy-MM-dd HH24:mi:ss') as TIMESTAMP,a.AC_PWR,a.INVERTERCODE from
DATA_INVERTER5MINDATA a,(select trunc(sysdate) as time from dual) b
where a.TIMESTAMP between b.time and (b.time + 1) )m)n
where rank = 1)s,DEV_INVERTER c,DEV_INVERTERMODEL d,DEV_COMBINERBOX g,DEV_BOXTRANS e,DEV_BUSLINE f
where s.INVERTERCODE = c.INVERTERCODE and c.MODELID = d.MODELID and c.TYPE = 3 and c.ONCODE = g.COMBINERBOXCODE and g.TYPE = 3 and g.ONCODE = e.BOXTRANSCODE and e.BUSLINECODE = f.BUSLINECODE
)
group by PROJECTCODE,MODEL)h,DEV_PROJECT k
where h.PROJECTCODE = k.PROJECTCODE;



-- STDDEV_POP(AC_PWR)  AS "STDDEV", --��׼��
----�ڶ���,ʱ��Ҳ��ȡ�ϱߵ�ʱ�䡣
--select to_char(TIMESTAMP,'yyyy-MM-dd HH24:mi:ss') as TIMESTAMP,AC_PWR,MODEL,PROJECTCODE from 
----���������
--(select s.TIMESTAMP,s.AC_PWR,d.MODEL,f.PROJECTCODE from
--(select n.TIMESTAMP,n.AC_PWR,n.INVERTERCODE from
--(select m.*,row_number() over (partition by m.INVERTERCODE order by m.TIMESTAMP desc) rank from 
--(select a.TIMESTAMP,a.AC_PWR,a.INVERTERCODE from
--DATA_INVERTER5MINDATA a,(select trunc(sysdate) as time from dual) b
--where a.TIMESTAMP between b.time and (b.time + 1) )m)n
--where rank = 1)s,DEV_INVERTER c,DEV_INVERTERMODEL d,DEV_BOXTRANS e,DEV_BUSLINE f
--where s.INVERTERCODE = c.INVERTERCODE and c.MODELID = d.MODELID and c.TYPE = 1 and c.ONCODE = e.BOXTRANSCODE and e.BUSLINECODE = f.BUSLINECODE
--union all
----��ɢ�����
--select s.TIMESTAMP,s.AC_PWR,d.MODEL,f.PROJECTCODE from
--(select n.TIMESTAMP,n.AC_PWR,n.INVERTERCODE from
--(select m.*,row_number() over (partition by m.INVERTERCODE order by m.TIMESTAMP desc) rank from 
--(select a.TIMESTAMP,a.AC_PWR,a.INVERTERCODE from
--DATA_INVERTER5MINDATA a,(select trunc(sysdate) as time from dual) b
--where a.TIMESTAMP between b.time and (b.time + 1) )m)n
--where rank = 1)s,DEV_INVERTER c,DEV_INVERTERMODEL d,DEV_BOXTRANS e,DEV_BUSLINE f
--where s.INVERTERCODE = c.INVERTERCODE and c.MODELID = d.MODELID and c.TYPE = 2 and c.ONCODE = e.BOXTRANSCODE and e.BUSLINECODE = f.BUSLINECODE
--union all
----�鴮�����
--select s.TIMESTAMP,s.AC_PWR,d.MODEL,f.PROJECTCODE from
--(select n.TIMESTAMP,n.AC_PWR,n.INVERTERCODE from
--(select m.*,row_number() over (partition by m.INVERTERCODE order by m.TIMESTAMP desc) rank from 
--(select a.TIMESTAMP,a.AC_PWR,a.INVERTERCODE from
--DATA_INVERTER5MINDATA a,(select trunc(sysdate) as time from dual) b
--where a.TIMESTAMP between b.time and (b.time + 1) )m)n
--where rank = 1)s,DEV_INVERTER c,DEV_INVERTERMODEL d,DEV_COMBINERBOX g,DEV_BOXTRANS e,DEV_BUSLINE f
--where s.INVERTERCODE = c.INVERTERCODE and c.MODELID = d.MODELID and c.TYPE = 3 and c.ONCODE = g.COMBINERBOXCODE and g.TYPE = 3 and g.ONCODE = e.BOXTRANSCODE and e.BUSLINECODE = f.BUSLINECODE
--);

--����������java��������


--���ĸ�
select h.times,h.STDDEV,h.MODEL,k.PROJECTCODE,k.PROJECTNAME from
(
select WMSYS.WM_CONCAT(TIMESTAMP) as times,STDDEV_POP(radio) as STDDEV,MODEL,PROJECTCODE from 
--���������
(select s.TIMESTAMP,s.radio,d.MODEL,f.PROJECTCODE from
(select n.TIMESTAMP,n.radio,n.INVERTERCODE from
(select m.*,row_number() over (partition by m.INVERTERCODE order by m.TIMESTAMP desc) rank from 
(select to_char(a.TIMESTAMP,'yyyy-MM-dd HH24:mi:ss') as TIMESTAMP,decode(a.DC_PWR,0,'0',AC_PWR/a.DC_PWR) as radio,a.INVERTERCODE from
DATA_INVERTER5MINDATA a,(select trunc(sysdate) as time from dual) b
where a.TIMESTAMP between b.time and (b.time + 1) )m)n
where rank = 1)s,DEV_INVERTER c,DEV_INVERTERMODEL d,DEV_BOXTRANS e,DEV_BUSLINE f
where s.INVERTERCODE = c.INVERTERCODE and c.MODELID = d.MODELID and c.TYPE = 1 and c.ONCODE = e.BOXTRANSCODE and e.BUSLINECODE = f.BUSLINECODE
union all
--��ɢ�����
select s.TIMESTAMP,s.radio,d.MODEL,f.PROJECTCODE from
(select n.TIMESTAMP,n.radio,n.INVERTERCODE from
(select m.*,row_number() over (partition by m.INVERTERCODE order by m.TIMESTAMP desc) rank from 
(select to_char(a.TIMESTAMP,'yyyy-MM-dd HH24:mi:ss') as TIMESTAMP,decode(a.DC_PWR,0,'0',AC_PWR/a.DC_PWR) as radio,a.INVERTERCODE from
DATA_INVERTER5MINDATA a,(select trunc(sysdate) as time from dual) b
where a.TIMESTAMP between b.time and (b.time + 1) )m)n
where rank = 1)s,DEV_INVERTER c,DEV_INVERTERMODEL d,DEV_BOXTRANS e,DEV_BUSLINE f
where s.INVERTERCODE = c.INVERTERCODE and c.MODELID = d.MODELID and c.TYPE = 2 and c.ONCODE = e.BOXTRANSCODE and e.BUSLINECODE = f.BUSLINECODE
union all
--�鴮�����
select s.TIMESTAMP,s.radio,d.MODEL,f.PROJECTCODE from
(select n.TIMESTAMP,n.radio,n.INVERTERCODE from
(select m.*,row_number() over (partition by m.INVERTERCODE order by m.TIMESTAMP desc) rank from 
(select to_char(a.TIMESTAMP,'yyyy-MM-dd HH24:mi:ss') as TIMESTAMP,decode(a.DC_PWR,0,'0',AC_PWR/a.DC_PWR) as radio,a.INVERTERCODE from
DATA_INVERTER5MINDATA a,(select trunc(sysdate) as time from dual) b
where a.TIMESTAMP between b.time and (b.time + 1) )m)n
where rank = 1)s,DEV_INVERTER c,DEV_INVERTERMODEL d,DEV_COMBINERBOX g,DEV_BOXTRANS e,DEV_BUSLINE f
where s.INVERTERCODE = c.INVERTERCODE and c.MODELID = d.MODELID and c.TYPE = 3 and c.ONCODE = g.COMBINERBOXCODE and g.TYPE = 3 and g.ONCODE = e.BOXTRANSCODE and e.BUSLINECODE = f.BUSLINECODE
)
group by PROJECTCODE,MODEL)h,DEV_PROJECT k
where h.PROJECTCODE = k.PROJECTCODE;

--�����������������˵ֻ��invertercode��Ψһ�ģ�Ȼ���������ϻ������¶���һ�Զ�������ȫ����ȥjava�����ﴦ��
select INVERTERCODE,PVSTRINGCODE,PVNUMBER,STANP,MODEL,PROJECTCODE from
(
--���������
select s.TIMESTAMP,s.INVERTERCODE,c.PVSTRINGCODE,d.PVNUMBER,e.STANP,g.MODEL,k.PROJECTCODE from
(select n.TIMESTAMP,n.INVERTERCODE from
(select m.*,row_number() over (partition by m.INVERTERCODE order by m.TIMESTAMP desc) rank from 
(select to_char(a.TIMESTAMP,'yyyy-MM-dd HH24:mi:ss') as TIMESTAMP,a.INVERTERCODE from
DATA_INVERTER5MINDATA a,(select trunc(sysdate) as time from dual) b
where a.TIMESTAMP between b.time and (b.time + 1) )m)n
where rank = 1) s,dev_CombinerBox v ,DEV_PVSTRING c,BAS_STRING d,DEV_PVMODEL e,
DEV_INVERTER f,DEV_INVERTERMODEL g,DEV_BOXTRANS i,DEV_BUSLINE k
where s.INVERTERCODE = v.ONCODE and v.TYPE = 1 and v.COMBINERBOXCODE = c.ONCODE and c.ONTYPE = 1 and c.ONESTRINGID = d.ONESTRINGID and c.MODELID = e.MODELID     
and s.INVERTERCODE = f.INVERTERCODE and f.MODELID = g.MODELID and f.TYPE = 1 and f.ONCODE = i.BOXTRANSCODE
and i.BUSLINECODE = k.BUSLINECODE
union all
--��ɢ�����
select s.TIMESTAMP,s.INVERTERCODE,c.PVSTRINGCODE,d.PVNUMBER,e.STANP,g.MODEL,k.PROJECTCODE from
(select n.TIMESTAMP,n.INVERTERCODE from
(select m.*,row_number() over (partition by m.INVERTERCODE order by m.TIMESTAMP desc) rank from 
(select to_char(a.TIMESTAMP,'yyyy-MM-dd HH24:mi:ss') as TIMESTAMP,a.INVERTERCODE from
DATA_INVERTER5MINDATA a,(select trunc(sysdate) as time from dual) b
where a.TIMESTAMP between b.time and (b.time + 1) )m)n
where rank = 1) s,dev_CombinerBox v ,DEV_PVSTRING c,BAS_STRING d,DEV_PVMODEL e,
DEV_INVERTER f,DEV_INVERTERMODEL g,DEV_BOXTRANS i,DEV_BUSLINE k
where s.INVERTERCODE = v.ONCODE and v.TYPE = 2 and v.COMBINERBOXCODE = c.ONCODE and c.ONTYPE = 2 and c.ONESTRINGID = d.ONESTRINGID and c.MODELID = e.MODELID     
and s.INVERTERCODE = f.INVERTERCODE and f.MODELID = g.MODELID and f.TYPE = 2 and f.ONCODE = i.BOXTRANSCODE
and i.BUSLINECODE = k.BUSLINECODE
union all
--�鴮�����
select s.TIMESTAMP,s.INVERTERCODE,c.PVSTRINGCODE,d.PVNUMBER,e.STANP,g.MODEL,k.PROJECTCODE from
(select n.TIMESTAMP,n.INVERTERCODE from
(select m.*,row_number() over (partition by m.INVERTERCODE order by m.TIMESTAMP desc) rank from 
(select to_char(a.TIMESTAMP,'yyyy-MM-dd HH24:mi:ss') as TIMESTAMP,a.INVERTERCODE from
DATA_INVERTER5MINDATA a,(select trunc(sysdate) as time from dual) b
where a.TIMESTAMP between b.time and (b.time + 1) )m)n
where rank = 1) s,DEV_PVSTRING c,BAS_STRING d,DEV_PVMODEL e,
DEV_INVERTER f,DEV_INVERTERMODEL g,DEV_COMBINERBOX h,DEV_BOXTRANS i,DEV_BUSLINE k
where s.INVERTERCODE = c.ONCODE and c.ONTYPE = 3 and c.ONESTRINGID = d.ONESTRINGID and c.MODELID = e.MODELID
and s.INVERTERCODE = f.INVERTERCODE and f.MODELID = g.MODELID and f.TYPE = 3 and f.ONCODE = h.COMBINERBOXCODE
and h.TYPE = 3 and h.ONCODE = i.BOXTRANSCODE and i.BUSLINECODE = k.BUSLINECODE)


--������
select h.times,h.avg,h.MODEL,k.PROJECTCODE,k.PROJECTNAME from
(select WMSYS.WM_CONCAT(TIMESTAMP) as times,avg(radio) as avg,MODEL,PROJECTCODE from 
(select s.TIMESTAMP,s.radio,d.MODEL,f.PROJECTCODE from
(select n.TIMESTAMP,n.radio,n.BOXTRANSCODE from
(select m.*,row_number() over (partition by m.BOXTRANSCODE order by m.TIMESTAMP desc) rank from 
(select to_char(a.TIMESTAMP,'yyyy-MM-dd HH24:mi:ss') as TIMESTAMP,decode(a.IN_PWR,0,'0',a.OUT_PWR/a.IN_PWR) as radio,a.BOXTRANSCODE from
DATA_BOXTRANS5MINDATA a,(select trunc(sysdate) as time from dual) b
where a.TIMESTAMP between b.time and (b.time + 1) )m)n
where rank = 1)s,DEV_BOXMODEL d,DEV_BOXTRANS e,DEV_BUSLINE f
where s.BOXTRANSCODE =  e.BOXTRANSCODE and e.MODELID = d.MODELID and e.BUSLINECODE = f.BUSLINECODE)
group by PROJECTCODE,MODEL)h,DEV_PROJECT k
where h.PROJECTCODE = k.PROJECTCODE;

--���Ƕ�Ӧ�ĸ���invertercode�����µ���������
select n.TIMESTAMP,n.INVERTERCODE from
(select m.*,row_number() over (partition by m.INVERTERCODE order by m.TIMESTAMP desc) rank from 
(select to_char(a.TIMESTAMP,'yyyy-MM-dd HH24:mi:ss') as TIMESTAMP,a.INVERTERCODE from
DATA_INVERTER5MINDATA a,(select trunc(sysdate) as time from dual) b
where a.TIMESTAMP between b.time and (b.time + 1) )m)n
where rank = 1



















