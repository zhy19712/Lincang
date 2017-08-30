-- �����װ��������5min�ı�����ݣ���Ȼ���б��վ��code�ͱ��վ��type
-- ������ѹվ��5min�ı��Ӧ�õ�������
select e.INSTALLCAPACITY,e.PWR,e.PACTELEC,e.SUBSTATIONCODE,e.SUBSTATIONTYPE,f.STATIONNAME,e.TIMESTAMP from
(select d.INSTALLCAPACITY,c.PWR,c.PACTELEC,c.SUBSTATIONCODE,c.SUBSTATIONTYPE,c.TIMESTAMP from
(select a.PWR,a.PACTELEC,b.SUBSTATIONCODE,b.SUBSTATIONTYPE��a.GRIDSWITCHCODE,a.TIMESTAMP from
(
select last.GRIDSWITCHCODE,(last.PWR-first.PWR)as PWR,(last.PACTELEC-first.PACTELEC) as PACTELEC,last.TIMESTAMP from
(select t.GRIDSWITCHCODE��t.PWR,t.PACTELEC,t.TIMESTAMP,t.rank from 
(select m.*, row_number() over (partition by m.GRIDSWITCHCODE order by m.TIMESTAMP desc) rank 
from (select a.GRIDSWITCHCODE,a.PWR,a.PACTELEC,a.TIMESTAMP from
(select GRIDSWITCHCODE,PWR,PACTELEC,TIMESTAMP from DATA_GRIDSWITCH5MINDATA)a ,(select trunc(sysdate) as time from dual) b 
where a.TIMESTAMP >= b.time and a.TIMESTAMP < (b.time + 1))m) t
where rank  = 1) last,
(select t.GRIDSWITCHCODE��t.PWR,t.PACTELEC,t.TIMESTAMP,t.rank from 
(select m.*, row_number() over (partition by m.GRIDSWITCHCODE order by m.TIMESTAMP asc) rank 
from (select a.GRIDSWITCHCODE,a.PWR,a.PACTELEC,a.TIMESTAMP from
(select GRIDSWITCHCODE,PWR,PACTELEC,TIMESTAMP from DATA_GRIDSWITCH5MINDATA)a ,(select trunc(sysdate) as time from dual) b 
where a.TIMESTAMP >= b.time and a.TIMESTAMP < (b.time + 1))m) t
where rank  = 1) first
where last.GRIDSWITCHCODE = first.GRIDSWITCHCODE

)a,DEV_GRIDSWITCH b
where a.GRIDSWITCHCODE = b.GRIDSWITCHCODE and b.SUBSTATIONTYPE = 1)c,DEV_ECONOMIC d
where c.SUBSTATIONCODE = d.SUBSTATIONCODE and c.SUBSTATIONTYPE = d.SUBSTATIONTYPE)e,DEV_STATION f
where e.SUBSTATIONCODE = f.STATIONCODE;



--����ʱ������ʱ��
select t.* from 
(select DATA_GRIDSWITCH5MINDATA.*, row_number() over (partition by GRIDSWITCHCODE order by TIMESTAMP desc) rank 
from DATA_GRIDSWITCH5MINDATA) t
where rank  = 1
-- ���sql���ڲ�ѯ���е��ڽ�����������ʱ��֮�������
select a.GRIDSWITCHCODE,a.PWR,a.PACTELEC,a.TIMESTAMP from
(select GRIDSWITCHCODE,PWR,PACTELEC,TIMESTAMP from DATA_GRIDSWITCH5MINDATA)a ,(select trunc(sysdate) as time from dual) b 
where a.TIMESTAMP between (b.time - 1)  and b.time
a.TIMESTAMP >= b.time and a.TIMESTAMP < (b.time + 1) 

--�����ϱ������ĺ��壬�൱������˰���code���黹�õ������ڵİ���ʱ�������ʱ������
select t.GRIDSWITCHCODE��t.PWR,t.PACTELEC,t.TIMESTAMP,t.rank from 
(select m.*, row_number() over (partition by m.GRIDSWITCHCODE order by m.TIMESTAMP desc) rank 
from (select a.GRIDSWITCHCODE,a.PWR,a.PACTELEC,a.TIMESTAMP from
(select GRIDSWITCHCODE,PWR,PACTELEC,TIMESTAMP from DATA_GRIDSWITCH5MINDATA)a ,(select trunc(sysdate) as time from dual) b 
where a.TIMESTAMP >= b.time and a.TIMESTAMP < (b.time + 1))m) t
where rank  = 1

select t.GRIDSWITCHCODE��t.PWR,t.PACTELEC,t.TIMESTAMP,t.rank from 
(select m.*, row_number() over (partition by m.GRIDSWITCHCODE order by m.TIMESTAMP asc) rank 
from (select a.GRIDSWITCHCODE,a.PWR,a.PACTELEC,a.TIMESTAMP from
(select GRIDSWITCHCODE,PWR,PACTELEC,TIMESTAMP from DATA_GRIDSWITCH5MINDATA)a ,(select trunc(sysdate) as time from dual) b 
where a.TIMESTAMP >= b.time and a.TIMESTAMP < (b.time + 1))m) t
where rank  = 1
--������Ǽ���֮���
select last.GRIDSWITCHCODE,(last.PWR-first.PWR)as PWR,(last.PACTELEC-first.PACTELEC) as PACTELEC from
(select t.GRIDSWITCHCODE��t.PWR,t.PACTELEC,t.TIMESTAMP,t.rank from 
(select m.*, row_number() over (partition by m.GRIDSWITCHCODE order by m.TIMESTAMP desc) rank 
from (select a.GRIDSWITCHCODE,a.PWR,a.PACTELEC,a.TIMESTAMP from
(select GRIDSWITCHCODE,PWR,PACTELEC,TIMESTAMP from DATA_GRIDSWITCH5MINDATA)a ,(select trunc(sysdate) as time from dual) b 
where a.TIMESTAMP >= b.time and a.TIMESTAMP < (b.time + 1))m) t
where rank  = 1) last,
(select t.GRIDSWITCHCODE��t.PWR,t.PACTELEC,t.TIMESTAMP,t.rank from 
(select m.*, row_number() over (partition by m.GRIDSWITCHCODE order by m.TIMESTAMP asc) rank 
from (select a.GRIDSWITCHCODE,a.PWR,a.PACTELEC,a.TIMESTAMP from
(select GRIDSWITCHCODE,PWR,PACTELEC,TIMESTAMP from DATA_GRIDSWITCH5MINDATA)a ,(select trunc(sysdate) as time from dual) b 
where a.TIMESTAMP >= b.time and a.TIMESTAMP < (b.time + 1))m) t
where rank  = 1) first
where last.GRIDSWITCHCODE = first.GRIDSWITCHCODE




--������ѹվ��10min�ı��Ӧ�õ�������
select c.CANTOTALRADVAL,d.STATIONNAME,c.SUBSTATIONCODE,c.SUBSTATIONTYPE,c.TIMESTAMP from
(select aa.CANTOTALRADVAL,bb.SUBSTATIONCODE,bb.SUBSTATIONTYPE,aa.TIMESTAMP from
(
select t.ENVMONITORCODE��t.CANTOTALRADVAL,t.TIMESTAMP,t.rank from 
(select m.*, row_number() over (partition by m.ENVMONITORCODE order by m.TIMESTAMP desc) rank 
from (select a.ENVMONITORCODE,a.CANTOTALRADVAL,a.TIMESTAMP from
(select ENVMONITORCODE,CANTOTALRADVAL,TIMESTAMP from DATA_ENV10MINDATA)a ,(select trunc(sysdate) as time from dual) b 
where a.TIMESTAMP >= b.time and a.TIMESTAMP < (b.time + 1))m) t
where rank  = 1
)aa,DEV_ENVMONITOR bb
where aa.ENVMONITORCODE = bb.ENVMONITORCODE and bb.SUBSTATIONTYPE = 1)c,DEV_STATION d
where c.SUBSTATIONCODE = d.STATIONCODE;

--�������ѯ10min�б��еĽ��������
select t.ENVMONITORCODE,t.CANTOTALRADVAL,t.TIMESTAMP,t.rank from 
(select m.*, row_number() over (partition by m.ENVMONITORCODE order by m.TIMESTAMP desc) rank 
from (select a.ENVMONITORCODE,a.CANTOTALRADVAL,a.TIMESTAMP from
(select ENVMONITORCODE,CANTOTALRADVAL,TIMESTAMP from DATA_ENV10MINDATA)a ,(select trunc(sysdate) as time from dual) b 
where a.TIMESTAMP >= b.time and a.TIMESTAMP < (b.time + 1))m) t
where rank  = 1




