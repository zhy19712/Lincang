--��STAT_PVMODULEDATA���м���model�ֶ�֮���sql��䡣
select sum(PVNUMBER) as PVMNUM,MODEL,PROJECTCODE from
(select k.PVNUMBER,k.MODEL,m.PROJECTCODE from
(select i.PVNUMBER,i.MODEL,j.BUSLINECODE from
(select g.PVNUMBER,g.MODEL,h.ONCODE from
(select e.PVNUMBER,e.MODEL,f.ONCODE from
(select a.ONCODE,b.PVNUMBER,c.MODEL from
DEV_PVSTRING a,BAS_STRING b,DEV_PVMODEL c
where a.ONESTRINGID = b. ONESTRINGID and a.MODELID = c.MODELID and a.ONTYPE = 1)e,DEV_COMBINERBOX f
where e.ONCODE = f.COMBINERBOXCODE and f.TYPE = 1)g, DEV_INVERTER h
where g.ONCODE = h.INVERTERCODE and h.TYPE = 1)i,DEV_BOXTRANS j
where i.ONCODE = j.BOXTRANSCODE)k,DEV_BUSLINE m
where k.BUSLINECODE = m.BUSLINECODE
union all
select k.PVNUMBER,k.MODEL,m.PROJECTCODE from
(select i.PVNUMBER,i.MODEL,j.BUSLINECODE from
(select g.PVNUMBER,g.MODEL,h.ONCODE from
(select e.PVNUMBER,e.MODEL,f.ONCODE from
(select a.ONCODE,b.PVNUMBER,c.MODEL from
DEV_PVSTRING a,BAS_STRING b,DEV_PVMODEL c
where a.ONESTRINGID = b. ONESTRINGID and a.MODELID = c.MODELID and a.ONTYPE = 2)e,DEV_COMBINERBOX f
where e.ONCODE = f.COMBINERBOXCODE and f.TYPE = 2)g, DEV_INVERTER h
where g.ONCODE = h.INVERTERCODE and h.TYPE = 2)i,DEV_BOXTRANS j
where i.ONCODE = j.BOXTRANSCODE)k,DEV_BUSLINE m
where k.BUSLINECODE = m.BUSLINECODE
union all
select k.PVNUMBER,k.MODEL,m.PROJECTCODE from
(select i.PVNUMBER,i.MODEL,j.BUSLINECODE from
(select g.PVNUMBER,g.MODEL,h.ONCODE from
(select e.PVNUMBER,e.MODEL,f.ONCODE from
(select a.ONCODE,b.PVNUMBER,c.MODEL from
DEV_PVSTRING a,BAS_STRING b,DEV_PVMODEL c
where a.ONESTRINGID = b. ONESTRINGID and a.MODELID = c.MODELID and a.ONTYPE = 3)e,DEV_INVERTER f
where e.ONCODE = f.INVERTERCODE and f.TYPE = 3)g, DEV_COMBINERBOX h
where g.ONCODE = h.COMBINERBOXCODE and h.TYPE = 3)i,DEV_BOXTRANS j
where i.ONCODE = j.BOXTRANSCODE)k,DEV_BUSLINE m
where k.BUSLINECODE = m.BUSLINECODE)
group by PROJECTCODE,MODEL;





--������Ŀ�ģ��������ݿ��н������ɾ���ˣ����ǹ���鴮���ϼ�������1�������͵�ʱ��
select sum(PVNUMBER),wmsys.wm_concat(MODEL) as MODEL,wmsys.wm_concat(MATERIALID) as MATERIALID,wmsys.wm_concat(VENDER) as VENDER from
(select a.ONCODE,b.PVNUMBER,c.MODEL,c.MATERIALID,e.VENDER from DEV_PVSTRING a,BAS_STRING b,DEV_PVMODEL c,BAS_VENDER e
where a.ONESTRINGID = b. ONESTRINGID and a.MODELID = c.MODELID and c.VENDERID = e.VENDERID and a.ONTYPE = 1)
group by ONCODE
--������Ŀ�ģ��������ݿ��е������ɾ���ˣ����ǹ���鴮���ϼ�������1�������͵�ʱ�������ҵ���ֵ�ı����ջ���Ҫ����projectcode������
select sum(PVMNUM) as PVMNUM,wmsys.wm_concat(MODEL) as MODEL,wmsys.wm_concat(CONVEREFFIC) as CONVEREFFIC,wmsys.wm_concat(DICTNAME) as DICTNAME,wmsys.wm_concat(VENDER) as VENDER,PROJECTCODE from
(
select k.PVMNUM,k.MODEL,k.CONVEREFFIC,k.DICTNAME,k.VENDER,m.PROJECTCODE from
(select i.PVMNUM,i.MODEL,i.CONVEREFFIC,i.DICTNAME,i.VENDER,j.BUSLINECODE from
(select g.PVMNUM,g.MODEL,g.CONVEREFFIC,g.DICTNAME,g.VENDER,h.ONCODE from
(select e.PVMNUM,e.MODEL,e.CONVEREFFIC,e.DICTNAME,e.VENDER,f.ONCODE from
(select sum(PVNUMBER) as PVMNUM,wmsys.wm_concat(MODEL) as MODEL,wmsys.wm_concat(CONVEREFFIC) as CONVEREFFIC,wmsys.wm_concat(DICTNAME) as DICTNAME,wmsys.wm_concat(VENDER) as VENDER,ONCODE from
(select a.ONCODE,a.ONTYPE,b.PVNUMBER,c.MODEL,c.CONVEREFFIC,d.DICTNAME,ee.VENDER from DEV_PVSTRING a,BAS_STRING b,DEV_PVMODEL c,EOS_DICT_ENTRY d,BAS_VENDER ee
where a.ONESTRINGID = b. ONESTRINGID and a.ONTYPE = 1 and a.MODELID = c.MODELID and c.MATERIALID = d.DICTID and d.DICTTYPEID = 'MATERIAL' and c.VENDERID = ee.VENDERID)
group by ONCODE)e,DEV_COMBINERBOX f
where e.ONCODE = f.COMBINERBOXCODE and f.TYPE = 1)g, DEV_INVERTER h
where g.ONCODE = h.INVERTERCODE and h.TYPE = 1)i,DEV_BOXTRANS j
where i.ONCODE = j.BOXTRANSCODE)k,DEV_BUSLINE m
where k.BUSLINECODE = m.BUSLINECODE
union all
--���ǹ���鴮�ϼ�������2�������͵�ʱ��
select k.PVMNUM,k.MODEL,k.CONVEREFFIC,k.DICTNAME,k.VENDER,m.PROJECTCODE from
(select i.PVMNUM,i.MODEL,i.CONVEREFFIC,i.DICTNAME,i.VENDER,j.BUSLINECODE from
(select g.PVMNUM,g.MODEL,g.CONVEREFFIC,g.DICTNAME,g.VENDER,h.ONCODE from
(select e.PVMNUM,e.MODEL,e.CONVEREFFIC,e.DICTNAME,e.VENDER,f.ONCODE from
(select sum(PVNUMBER) as PVMNUM,wmsys.wm_concat(MODEL) as MODEL,wmsys.wm_concat(CONVEREFFIC) as CONVEREFFIC,wmsys.wm_concat(DICTNAME) as DICTNAME,wmsys.wm_concat(VENDER) as VENDER,ONCODE from
(select a.ONCODE,a.ONTYPE,b.PVNUMBER,c.MODEL,c.CONVEREFFIC,d.DICTNAME,ee.VENDER from DEV_PVSTRING a,BAS_STRING b,DEV_PVMODEL c,EOS_DICT_ENTRY d,BAS_VENDER ee
where a.ONESTRINGID = b. ONESTRINGID and a.ONTYPE = 2 and a.MODELID = c.MODELID and c.MATERIALID = d.DICTID and d.DICTTYPEID = 'MATERIAL' and c.VENDERID = ee.VENDERID)
group by ONCODE)e,DEV_COMBINERBOX f
where e.ONCODE = f.COMBINERBOXCODE and f.TYPE = 2)g, DEV_INVERTER h
where g.ONCODE = h.INVERTERCODE and h.TYPE = 2)i,DEV_BOXTRANS j
where i.ONCODE = j.BOXTRANSCODE)k,DEV_BUSLINE m
where k.BUSLINECODE = m.BUSLINECODE
union all
--���ǹ���鴮�ϼ�������3�������͵�ʱ��
select k.PVMNUM,k.MODEL,k.CONVEREFFIC,k.DICTNAME,k.VENDER,m.PROJECTCODE from
(select i.PVMNUM,i.MODEL,i.CONVEREFFIC,i.DICTNAME,i.VENDER,j.BUSLINECODE from
(select g.PVMNUM,g.MODEL,g.CONVEREFFIC,g.DICTNAME,g.VENDER,h.ONCODE from
(select e.PVMNUM,e.MODEL,e.CONVEREFFIC,e.DICTNAME,e.VENDER,f.ONCODE from
(select sum(PVNUMBER) as PVMNUM,wmsys.wm_concat(MODEL) as MODEL,wmsys.wm_concat(CONVEREFFIC) as CONVEREFFIC,wmsys.wm_concat(DICTNAME) as DICTNAME,wmsys.wm_concat(VENDER) as VENDER,ONCODE from
(select a.ONCODE,a.ONTYPE,b.PVNUMBER,c.MODEL,c.CONVEREFFIC,d.DICTNAME,ee.VENDER from DEV_PVSTRING a,BAS_STRING b,DEV_PVMODEL c,EOS_DICT_ENTRY d,BAS_VENDER ee
where a.ONESTRINGID = b. ONESTRINGID and a.ONTYPE = 3 and a.MODELID = c.MODELID and c.MATERIALID = d.DICTID and d.DICTTYPEID = 'MATERIAL' and c.VENDERID = ee.VENDERID)
group by ONCODE)e,DEV_INVERTER f
where e.ONCODE = f.INVERTERCODE and f.TYPE = 3)g,DEV_COMBINERBOX h
where g.ONCODE = h.COMBINERBOXCODE and h.TYPE = 3)i,DEV_BOXTRANS j
where i.ONCODE = j.BOXTRANSCODE)k,DEV_BUSLINE m
where k.BUSLINECODE = m.BUSLINECODE)
group by PROJECTCODE





-- ��ѹվ-��ѯ���,���ǲ�ѯ���鴮���ϼ���һ����
select c.num,c.modelid,c.venderid,d.ONTYPE,d.ONCODE from
(select count(a.PVSTRINGCODE)as num,wmsys.wm_concat(a.MODELID) as modelid,wmsys.wm_concat(b.VENDERID) as venderid,a.PVSTRINGCODE from 
DEV_PVMODULE a,DEV_PVMODEL b 
where a.MODELID = b.MODELID
group by a.PVSTRINGCODE) c,DEV_PVSTRING d
where c.PVSTRINGCODE = d.PVSTRINGCODE;
-- ��ѹվ-�鴮���������·
select sum(num),wmsys.wm_concat(modelid),wmsys.wm_concat(venderid),SUBSTATIONCODE from(
select sum(num) as num,wmsys.wm_concat(modelid) as modelid,wmsys.wm_concat(venderid) as venderid,n.SUBSTATIONCODE from
(select k.num,k.modelid,k.venderid,m.SUBSTATIONCODE,m.SUBSTATIONTYPE from
(select i.num,i.modelid,i.venderid,j.BUSLINECODE from
(select g.num,g.modelid,g.venderid,h.ONCODE,h.TYPE from
(select e.num,e.modelid,e.venderid,f.ONCODE,f.TYPE from
(select c.num,c.modelid,c.venderid,d.ONTYPE,d.ONCODE from
(select count(a.PVSTRINGCODE)as num,wmsys.wm_concat(a.MODELID) as modelid,wmsys.wm_concat(b.VENDERID) as venderid,a.PVSTRINGCODE from 
DEV_PVMODULE a,DEV_PVMODEL b 
where a.MODELID = b.MODELID
group by a.PVSTRINGCODE) c,DEV_PVSTRING d
where c.PVSTRINGCODE = d.PVSTRINGCODE)e, DEV_INVERTER f
where e.ONCODE = f.INVERTERCODE and f.TYPE = 3)g,DEV_COMBINERBOX h
where g.ONCODE = h.COMBINERBOXCODE and h.TYPE = 3)i,DEV_BOXTRANS j
where i.ONCODE = j.BOXTRANSCODE)k,DEV_BUSLINE m
where k.BUSLINECODE = M.BUSLINECODE)n,(select a.STATIONCODE,a.STATIONNAME from DEV_STATION a,DEV_BUSLINE b where a.STATIONCODE = b.SUBSTATIONCODE and b.SUBSTATIONTYPE = 1 order by b.SUBSTATIONCODE)o
where n.SUBSTATIONCODE = o.STATIONCODE and n.SUBSTATIONTYPE = 1
group by n.SUBSTATIONCODE
union all
--��ѹվ-����ֱ������������·
select sum(num) as num,wmsys.wm_concat(modelid) as modelid,wmsys.wm_concat(venderid) as venderid,n.SUBSTATIONCODE from
(select k.num,k.modelid,k.venderid,m.SUBSTATIONCODE,m.SUBSTATIONTYPE from
(select i.num,i.modelid,i.venderid,j.BUSLINECODE from
(select g.num,g.modelid,g.venderid,h.ONCODE,h.TYPE from
(select e.num,e.modelid,e.venderid,f.ONCODE,f.TYPE from
(select c.num,c.modelid,c.venderid,d.ONTYPE,d.ONCODE from
(select count(a.PVSTRINGCODE)as num,wmsys.wm_concat(a.MODELID) as modelid,wmsys.wm_concat(b.VENDERID) as venderid,a.PVSTRINGCODE from 
DEV_PVMODULE a,DEV_PVMODEL b 
where a.MODELID = b.MODELID
group by a.PVSTRINGCODE) c,DEV_PVSTRING d
where c.PVSTRINGCODE = d.PVSTRINGCODE)e, DEV_COMBINERBOX f
where e.ONCODE = f.COMBINERBOXCODE and f.TYPE = 1)g, DEV_INVERTER h
where g.ONCODE = h.INVERTERCODE and h.TYPE = 1)i,DEV_BOXTRANS j
where i.ONCODE = j.BOXTRANSCODE)k,DEV_BUSLINE m
where k.BUSLINECODE = M.BUSLINECODE)n,(select a.STATIONCODE,a.STATIONNAME from DEV_STATION a,DEV_BUSLINE b where a.STATIONCODE = b.SUBSTATIONCODE and b.SUBSTATIONTYPE = 1 order by b.SUBSTATIONCODE)o
where n.SUBSTATIONCODE = o.STATIONCODE and n.SUBSTATIONTYPE = 1
group by n.SUBSTATIONCODE
union all
--��ѹվ-��ɢֱ��������
select sum(num) as num,wmsys.wm_concat(modelid) as modelid,wmsys.wm_concat(venderid) as venderid,n.SUBSTATIONCODE from
(select k.num,k.modelid,k.venderid,m.SUBSTATIONCODE,m.SUBSTATIONTYPE from
(select i.num,i.modelid,i.venderid,j.BUSLINECODE from
(select g.num,g.modelid,g.venderid,h.ONCODE,h.TYPE from
(select e.num,e.modelid,e.venderid,f.ONCODE,f.TYPE from
(select c.num,c.modelid,c.venderid,d.ONTYPE,d.ONCODE from
(select count(a.PVSTRINGCODE)as num,wmsys.wm_concat(a.MODELID) as modelid,wmsys.wm_concat(b.VENDERID) as venderid,a.PVSTRINGCODE from 
DEV_PVMODULE a,DEV_PVMODEL b 
where a.MODELID = b.MODELID
group by a.PVSTRINGCODE) c,DEV_PVSTRING d
where c.PVSTRINGCODE = d.PVSTRINGCODE)e, DEV_COMBINERBOX f
where e.ONCODE = f.COMBINERBOXCODE and f.TYPE = 2)g, DEV_INVERTER h
where g.ONCODE = h.INVERTERCODE and h.TYPE = 2)i,DEV_BOXTRANS j
where i.ONCODE = j.BOXTRANSCODE)k,DEV_BUSLINE m
where k.BUSLINECODE = M.BUSLINECODE)n,(select a.STATIONCODE,a.STATIONNAME from DEV_STATION a,DEV_BUSLINE b where a.STATIONCODE = b.SUBSTATIONCODE and b.SUBSTATIONTYPE = 1 order by b.SUBSTATIONCODE)o
where n.SUBSTATIONCODE = o.STATIONCODE and n.SUBSTATIONTYPE = 1
group by n.SUBSTATIONCODE)
group by SUBSTATIONCODE
order by SUBSTATIONCODE;
--�㼯վʣ�µ�����
select sum(num),wmsys.wm_concat(modelid),wmsys.wm_concat(venderid),SUBSTATIONCODE from(
select sum(num) as num,wmsys.wm_concat(modelid) as modelid,wmsys.wm_concat(venderid) as venderid,n.SUBSTATIONCODE from
(select k.num,k.modelid,k.venderid,m.SUBSTATIONCODE,m.SUBSTATIONTYPE from
(select i.num,i.modelid,i.venderid,j.BUSLINECODE from
(select g.num,g.modelid,g.venderid,h.ONCODE,h.TYPE from
(select e.num,e.modelid,e.venderid,f.ONCODE,f.TYPE from
(select c.num,c.modelid,c.venderid,d.ONTYPE,d.ONCODE from
(select count(a.PVSTRINGCODE)as num,wmsys.wm_concat(a.MODELID) as modelid,wmsys.wm_concat(b.VENDERID) as venderid,a.PVSTRINGCODE from 
DEV_PVMODULE a,DEV_PVMODEL b 
where a.MODELID = b.MODELID
group by a.PVSTRINGCODE) c,DEV_PVSTRING d
where c.PVSTRINGCODE = d.PVSTRINGCODE)e, DEV_INVERTER f
where e.ONCODE = f.INVERTERCODE and f.TYPE = 3)g,DEV_COMBINERBOX h
where g.ONCODE = h.COMBINERBOXCODE and h.TYPE = 3)i,DEV_BOXTRANS j
where i.ONCODE = j.BOXTRANSCODE)k,DEV_BUSLINE m
where k.BUSLINECODE = M.BUSLINECODE)n,(select a.COLLSTATIONCODE,a.COLLSTATIONNAME from DEV_COLLSTATION a,DEV_BUSLINE b where a.COLLSTATIONCODE = b.SUBSTATIONCODE and b.SUBSTATIONTYPE = 2 order by b.SUBSTATIONCODE)o
where n.SUBSTATIONCODE = o.COLLSTATIONCODE and n.SUBSTATIONTYPE = 2
group by n.SUBSTATIONCODE
union all
select sum(num) as num,wmsys.wm_concat(modelid) as modelid,wmsys.wm_concat(venderid) as venderid,n.SUBSTATIONCODE from
(select k.num,k.modelid,k.venderid,m.SUBSTATIONCODE,m.SUBSTATIONTYPE from
(select i.num,i.modelid,i.venderid,j.BUSLINECODE from
(select g.num,g.modelid,g.venderid,h.ONCODE,h.TYPE from
(select e.num,e.modelid,e.venderid,f.ONCODE,f.TYPE from
(select c.num,c.modelid,c.venderid,d.ONTYPE,d.ONCODE from
(select count(a.PVSTRINGCODE)as num,wmsys.wm_concat(a.MODELID) as modelid,wmsys.wm_concat(b.VENDERID) as venderid,a.PVSTRINGCODE from 
DEV_PVMODULE a,DEV_PVMODEL b 
where a.MODELID = b.MODELID
group by a.PVSTRINGCODE) c,DEV_PVSTRING d
where c.PVSTRINGCODE = d.PVSTRINGCODE)e, DEV_COMBINERBOX f
where e.ONCODE = f.COMBINERBOXCODE and f.TYPE = 1)g, DEV_INVERTER h
where g.ONCODE = h.INVERTERCODE and h.TYPE = 1)i,DEV_BOXTRANS j
where i.ONCODE = j.BOXTRANSCODE)k,DEV_BUSLINE m
where k.BUSLINECODE = M.BUSLINECODE)n,(select a.COLLSTATIONCODE,a.COLLSTATIONNAME from DEV_COLLSTATION a,DEV_BUSLINE b where a.COLLSTATIONCODE = b.SUBSTATIONCODE and b.SUBSTATIONTYPE = 2 order by b.SUBSTATIONCODE)o
where n.SUBSTATIONCODE = o.COLLSTATIONCODE and n.SUBSTATIONTYPE = 2
group by n.SUBSTATIONCODE
union all
select sum(num) as num,wmsys.wm_concat(modelid) as modelid,wmsys.wm_concat(venderid) as venderid,n.SUBSTATIONCODE from
(select k.num,k.modelid,k.venderid,m.SUBSTATIONCODE,m.SUBSTATIONTYPE from
(select i.num,i.modelid,i.venderid,j.BUSLINECODE from
(select g.num,g.modelid,g.venderid,h.ONCODE,h.TYPE from
(select e.num,e.modelid,e.venderid,f.ONCODE,f.TYPE from
(select c.num,c.modelid,c.venderid,d.ONTYPE,d.ONCODE from
(select count(a.PVSTRINGCODE)as num,wmsys.wm_concat(a.MODELID) as modelid,wmsys.wm_concat(b.VENDERID) as venderid,a.PVSTRINGCODE from 
DEV_PVMODULE a,DEV_PVMODEL b 
where a.MODELID = b.MODELID
group by a.PVSTRINGCODE) c,DEV_PVSTRING d
where c.PVSTRINGCODE = d.PVSTRINGCODE)e, DEV_COMBINERBOX f
where e.ONCODE = f.COMBINERBOXCODE and f.TYPE = 2)g, DEV_INVERTER h
where g.ONCODE = h.INVERTERCODE and h.TYPE = 2)i,DEV_BOXTRANS j
where i.ONCODE = j.BOXTRANSCODE)k,DEV_BUSLINE m
where k.BUSLINECODE = M.BUSLINECODE)n,(select a.COLLSTATIONCODE,a.COLLSTATIONNAME from DEV_COLLSTATION a,DEV_BUSLINE b where a.COLLSTATIONCODE = b.SUBSTATIONCODE and b.SUBSTATIONTYPE = 2 order by b.SUBSTATIONCODE)o
where n.SUBSTATIONCODE = o.COLLSTATIONCODE and n.SUBSTATIONTYPE = 2
group by n.SUBSTATIONCODE)
group by SUBSTATIONCODE
order by SUBSTATIONCODE;