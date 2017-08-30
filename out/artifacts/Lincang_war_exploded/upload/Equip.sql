--先去逆变器5分钟数据表中取数据
select * from DATA_INVERTER5MINDATA;

select t.GRIDSWITCHCODE,t.PWR,t.PACTELEC,t.TIMESTAMP,t.rank from 
(select m.INVERTERCODE,m.AC_PWR,m.DC_PWR,m.TIMESTAMP, row_number() over (partition by m.INVERTERCODE order by m.TIMESTAMP desc) rank 
from (select a.INVERTERCODE,a.AC_PWR,a.DC_PWR,a.TIMESTAMP from
(select INVERTERCODE,AC_PWR,DC_PWR,TIMESTAMP from DATA_INVERTER5MINDATA)a ,(select trunc(sysdate) as time from dual) b 
where a.TIMESTAMP between b.time and (b.time + 1))m) t
where rank  = 1;

select t.GRIDSWITCHCODE，t.PWR,t.PACTELEC,t.TIMESTAMP,t.rank from 
(select m.*, row_number() over (partition by m.GRIDSWITCHCODE order by m.TIMESTAMP desc) rank 
from (select a.GRIDSWITCHCODE,a.PWR,a.PACTELEC,a.TIMESTAMP from
(select GRIDSWITCHCODE,PWR,PACTELEC,TIMESTAMP from DATA_GRIDSWITCH5MINDATA)a ,(select trunc(sysdate) as time from dual) b 
where a.TIMESTAMP >= b.time and a.TIMESTAMP < (b.time + 1))m) t
where rank  = 1