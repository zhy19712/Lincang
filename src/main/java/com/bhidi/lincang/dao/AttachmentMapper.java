package com.bhidi.lincang.dao;

import com.bhidi.lincang.bean.Attachment;
import org.springframework.stereotype.Repository;

@Repository
public interface AttachmentMapper {
    int save(Attachment att);
}
