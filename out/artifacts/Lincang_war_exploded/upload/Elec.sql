-- 查出来装机容量，5min的表的数据，当然还有变电站的code和变电站的type
-- 这是升压站和5min的表对应拿到的数据
select e.INSTALLCAPACITY,e.PWR,e.PACTELEC,e.SUBSTATIONCODE,e.SUBSTATIONTYPE,f.STATIONNAME,e.TIMESTAMP from
(select d.INSTALLCAPACITY,c.PWR,c.PACTELEC,c.SUBSTATIONCODE,c.SUBSTATIONTYPE,c.TIMESTAMP from
(select a.PWR,a.PACTELEC,b.SUBSTATIONCODE,b.SUBSTATIONTYPE，a.GRIDSWITCHCODE,a.TIMESTAMP from
(
select last.GRIDSWITCHCODE,(last.PWR-first.PWR)as PWR,(last.PACTELEC-first.PACTELEC) as PACTELEC,last.TIMESTAMP from
(select t.GRIDSWITCHCODE，t.PWR,t.PACTELEC,t.TIMESTAMP,t.rank from 
(select m.*, row_number() over (partition by m.GRIDSWITCHCODE order by m.TIMESTAMP desc) rank 
from (select a.GRIDSWITCHCODE,a.PWR,a.PACTELEC,a.TIMESTAMP from
(select GRIDSWITCHCODE,PWR,PACTELEC,TIMESTAMP from DATA_GRIDSWITCH5MINDATA)a ,(select trunc(sysdate) as time from dual) b 
where a.TIMESTAMP >= b.time and a.TIMESTAMP < (b.time + 1))m) t
where rank  = 1) last,
(select t.GRIDSWITCHCODE，t.PWR,t.PACTELEC,t.TIMESTAMP,t.rank from 
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



--这是时间最大的时候
select t.* from 
(select DATA_GRIDSWITCH5MINDATA.*, row_number() over (partition by GRIDSWITCHCODE order by TIMESTAMP desc) rank 
from DATA_GRIDSWITCH5MINDATA) t
where rank  = 1
-- 这段sql是在查询所有的在今天和明天这个时间之间的数据
select a.GRIDSWITCHCODE,a.PWR,a.PACTELEC,a.TIMESTAMP from
(select GRIDSWITCHCODE,PWR,PACTELEC,TIMESTAMP from DATA_GRIDSWITCH5MINDATA)a ,(select trunc(sysdate) as time from dual) b 
where a.TIMESTAMP between (b.time - 1)  and b.time
a.TIMESTAMP >= b.time and a.TIMESTAMP < (b.time + 1) 

--这是上边两个的合体，相当于完成了按照code分组还拿到了组内的按照时间排序的时间最大的
select t.GRIDSWITCHCODE，t.PWR,t.PACTELEC,t.TIMESTAMP,t.rank from 
(select m.*, row_number() over (partition by m.GRIDSWITCHCODE order by m.TIMESTAMP desc) rank 
from (select a.GRIDSWITCHCODE,a.PWR,a.PACTELEC,a.TIMESTAMP from
(select GRIDSWITCHCODE,PWR,PACTELEC,TIMESTAMP from DATA_GRIDSWITCH5MINDATA)a ,(select trunc(sysdate) as time from dual) b 
where a.TIMESTAMP >= b.time and a.TIMESTAMP < (b.time + 1))m) t
where rank  = 1

select t.GRIDSWITCHCODE，t.PWR,t.PACTELEC,t.TIMESTAMP,t.rank from 
(select m.*, row_number() over (partition by m.GRIDSWITCHCODE order by m.TIMESTAMP asc) rank 
from (select a.GRIDSWITCHCODE,a.PWR,a.PACTELEC,a.TIMESTAMP from
(select GRIDSWITCHCODE,PWR,PACTELEC,TIMESTAMP from DATA_GRIDSWITCH5MINDATA)a ,(select trunc(sysdate) as time from dual) b 
where a.TIMESTAMP >= b.time and a.TIMESTAMP < (b.time + 1))m) t
where rank  = 1
--这个就是减完之后的
select last.GRIDSWITCHCODE,(last.PWR-first.PWR)as PWR,(last.PACTELEC-first.PACTELEC) as PACTELEC from
(select t.GRIDSWITCHCODE，t.PWR,t.PACTELEC,t.TIMESTAMP,t.rank from 
(select m.*, row_number() over (partition by m.GRIDSWITCHCODE order by m.TIMESTAMP desc) rank 
from (select a.GRIDSWITCHCODE,a.PWR,a.PACTELEC,a.TIMESTAMP from
(select GRIDSWITCHCODE,PWR,PACTELEC,TIMESTAMP from DATA_GRIDSWITCH5MINDATA)a ,(select trunc(sysdate) as time from dual) b 
where a.TIMESTAMP >= b.time and a.TIMESTAMP < (b.time + 1))m) t
where rank  = 1) last,
(select t.GRIDSWITCHCODE，t.PWR,t.PACTELEC,t.TIMESTAMP,t.rank from 
(select m.*, row_number() over (partition by m.GRIDSWITCHCODE order by m.TIMESTAMP asc) rank 
from (select a.GRIDSWITCHCODE,a.PWR,a.PACTELEC,a.TIMESTAMP from
(select GRIDSWITCHCODE,PWR,PACTELEC,TIMESTAMP from DATA_GRIDSWITCH5MINDATA)a ,(select trunc(sysdate) as time from dual) b 
where a.TIMESTAMP >= b.time and a.TIMESTAMP < (b.time + 1))m) t
where rank  = 1) first
where last.GRIDSWITCHCODE = first.GRIDSWITCHCODE




--这是升压站和10min的表对应拿到的数据
select c.CANTOTALRADVAL,d.STATIONNAME,c.SUBSTATIONCODE,c.SUBSTATIONTYPE,c.TIMESTAMP from
(select aa.CANTOTALRADVAL,bb.SUBSTATIONCODE,bb.SUBSTATIONTYPE,aa.TIMESTAMP from
(
select t.ENVMONITORCODE，t.CANTOTALRADVAL,t.TIMESTAMP,t.rank from 
(select m.*, row_number() over (partition by m.ENVMONITORCODE order by m.TIMESTAMP desc) rank 
from (select a.ENVMONITORCODE,a.CANTOTALRADVAL,a.TIMESTAMP from
(select ENVMONITORCODE,CANTOTALRADVAL,TIMESTAMP from DATA_ENV10MINDATA)a ,(select trunc(sysdate) as time from dual) b 
where a.TIMESTAMP >= b.time and a.TIMESTAMP < (b.time + 1))m) t
where rank  = 1
)aa,DEV_ENVMONITOR bb
where aa.ENVMONITORCODE = bb.ENVMONITORCODE and bb.SUBSTATIONTYPE = 1)c,DEV_STATION d
where c.SUBSTATIONCODE = d.STATIONCODE;

--在这里查询10min中表中的今天的数据
select t.ENVMONITORCODE,t.CANTOTALRADVAL,t.TIMESTAMP,t.rank from 
(select m.*, row_number() over (partition by m.ENVMONITORCODE order by m.TIMESTAMP desc) rank 
from (select a.ENVMONITORCODE,a.CANTOTALRADVAL,a.TIMESTAMP from
(select ENVMONITORCODE,CANTOTALRADVAL,TIMESTAMP from DATA_ENV10MINDATA)a ,(select trunc(sysdate) as time from dual) b 
where a.TIMESTAMP >= b.time and a.TIMESTAMP < (b.time + 1))m) t
where rank  = 1




