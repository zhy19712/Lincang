--对于这个表来说，全是取最近时间的数据，然后实时覆盖。
--各站水平面总辐射	Z_Thoriradi,引用环境监测设备表(dev_EnvMonitor)—＞光资源测量10分钟数据表(data_Env10minData)中的相应的平均值（gradavg）
--各站倾斜面总辐射	Z_Tincliradi,引用环境监测设备表(dev_EnvMonitor)—＞光资源测量10分钟数据表(data_Env10minData)中的相应的平均值（Canradavg）
--各站直射Z_Direct,引用环境监测设备表(dev_EnvMonitor)—＞光资源测量10分钟数据表(data_Env10minData)中的相应的平均值（dradavg）
--各站散射Z_Dispersion,引用环境监测设备表(dev_EnvMonitor)—＞光资源测量10分钟数据表(data_Env10minData)中的相应的平均值（Sradavg）
--各站日照时数Z_Dlighth,引用环境监测设备表(dev_EnvMonitor)—＞光资源测量10分钟数据表(data_Env10minData)中的日照时数（TOTALRADHOUR）
--各站温度Z_Temp,引用环境监测设备表(dev_EnvMonitor) —＞光资源测量10分钟数据表(data_Env10minData)中的字段：温度10分钟平均值（Tempavg）
--各站风速Z_Wind,引用环境监测设备表(dev_EnvMonitor) —＞光资源测量10分钟数据表(data_Env10minData)中的字段：风速10分钟平均值（Winspavg）
select n.GRADAVG as Z_Thoriradi,n.CANRADAVG as Z_Tincliradi,n.DRADAVG as Z_Direct,n.SRADAVG as Z_Dispersion,n.TOTALRADHOUR as Z_Dlighth,n.TEMPAVG as Z_Temp,n.WINSPAVG as Z_Wind,to_char(n.TIMESTAMP,'yyyy-MM-dd HH24:mi:ss') as TIMESTAMP,n.PROJECTCODE,e.PROJECTNAME from
(select m.*,row_number() over (partition by m.PROJECTCODE order by m.TIMESTAMP desc) rank from 
(select a.GRADAVG,a.CANRADAVG,a.DRADAVG,a.SRADAVG,a.TOTALRADHOUR,a.TEMPAVG,a.WINSPAVG,a.TIMESTAMP,d.PROJECTCODE from
DATA_ENV10MINDATA a,(select trunc(sysdate) as time from dual) b,DEV_ENVMONITOR c,DEV_BUSLINE d
where a.TIMESTAMP between b.time and (b.time + 1) and a.ENVMONITORCODE = c.ENVMONITORCODE and
c.SUBSTATIONCODE = d.SUBSTATIONCODE and c.SUBSTATIONTYPE = d.SUBSTATIONTYPE)m)n,DEV_PROJECT e
where rank = 1 and n.PROJECTCODE = e.PROJECTCODE;


--各站总辐射年累计值,Z_YearTOTALRADVAL,引用环境监测设备表(dev_EnvMonitor) —＞光资源测量10分钟数据表(data_Env10minData)中的字段：总辐射当天总累计值（TOTALRADVAL）当年所有值求和
--去取时间最大的那个。这个时间是拼串拼的。这个时间不去了，就使用上边的时间
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


--各站实时有功功率	D_pwr,并网点5分钟数据表(data_GridSwitch5minData)中的字段有功功率（pwr）
select e.TIMESTAMP,e.DPWR,f.PROJECTCODE,f.PROJECTNAME from
(select to_char(n.TIMESTAMP,'yyyy-MM-dd HH24:mi:ss') as TIMESTAMP,n.PWR as DPWR,n.PROJECTCODE from
(select m.*,row_number() over (partition by m.PROJECTCODE order by m.TIMESTAMP desc) rank from 
(select TIMESTAMP,a.PWR,d.PROJECTCODE from
DATA_GRIDSWITCH5MINDATA a,(select trunc(sysdate) as time from dual) b,DEV_GRIDSWITCH c,DEV_BUSLINE d
where a.TIMESTAMP between b.time and (b.time + 1) and a.GRIDSWITCHCODE = c.GRIDSWITCHCODE and
c.SUBSTATIONTYPE = d.SUBSTATIONTYPE and c.SUBSTATIONCODE = d.SUBSTATIONCODE)m)n
where n.rank = 1)e,DEV_PROJECT f
where e.PROJECTCODE = f.PROJECTCODE;

--各站当天实时理论发电量，D_Theogen	，光资源测量10分钟数据表(data_Env10minData)中的相应的斜面辐射当天总累计值（CANTOTALRADVAL）中3点到统计时间点的差值乘以装机容量
--各站当天实时理论发电小时数,D_Theogenh,光资源测量10分钟数据表(data_Env10minData)中的相应的斜面辐射当天总累计值（CANTOTALRADVAL）中3点到统计时间点的差值
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

--各站当天实时上网电量	D_Amountpower，并网点5分钟数据表（data_GridSwitch5minData)中的字段正向有功电量（pActelec）中4点到统计时间点的差值
--各站当天实时上网电量有效小时数	D_Amountpowerh，并网点5分钟数据表（data_GridSwitch5minData)中的字段正向有功电量（pActelec）中3点到统计时间点的差值
--除以各站的装机容量
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


--各站当天实时上网电量/理论发电量比例，D_Genratio	，上网电量(Amountpower)除以理论发电量(Theogen)
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





--各站累计减排可以种植树,D_tree	Number(10),	并网点5分钟数据表（data_GridSwitch5minData)中的字段正向有功电量（pActelec）除以20.7再除以10000(注:20.7度电≈1棵树)
select to_char(e.TIMESTAMP,'yyyy-MM-dd HH24:mi:ss') as TIMESTAMP,e.PACTELEC,f.PROJECTCODE,f.PROJECTNAME,f.CAPACITY from
(select n.TIMESTAMP,n.PACTELEC,n.PROJECTCODE from
(select m.*,row_number() over (partition by m.PROJECTCODE order by m.TIMESTAMP desc) rank from 
(select a.TIMESTAMP,a.PACTELEC,d.PROJECTCODE from
DATA_GRIDSWITCH5MINDATA a,(select trunc(sysdate) as time from dual) b,DEV_GRIDSWITCH c,DEV_BUSLINE d
where a.TIMESTAMP between b.time and (b.time + 1) and a.GRIDSWITCHCODE = c.GRIDSWITCHCODE and
c.SUBSTATIONTYPE = d.SUBSTATIONTYPE and c.SUBSTATIONCODE = d.SUBSTATIONCODE)m)n
where n.rank = 1)e,DEV_PROJECT f
where e.PROJECTCODE = f.PROJECTCODE







--所有站累计实际上网电量,D_power,所有站并网点5分钟数据表（data_GridSwitch5minData)中的字段正向有功电量（pActelec）求和
--所有站累计实际上网电量有效小时数,D_powerh,所有站并网点5分钟数据表（data_GridSwitch5minData)中的字段正向有功电量（pActelec）除以各站的装机容量求和
--在这里有一个问题，所有站之后，项目不知道填什么。
--在10.1版本之后，说是所有站的舍弃掉，不需要写了。
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