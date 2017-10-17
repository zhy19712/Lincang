package com.bhidi.lincang.service;

import com.bhidi.lincang.bean.Attachment;
import com.bhidi.lincang.dao.AttachmentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AttachmentServiceImp implements AttachmentServiceInf {
    @Autowired
    AttachmentMapper attachmentMapper;



    public int save(Attachment att) {
        return attachmentMapper.save(att);
    }
}
