package com.nhnacademy.minidooray.gateway.service;

import com.nhnacademy.minidooray.gateway.domain.Tag;

import java.util.List;

public interface TagService {
    List<Tag> getTags(Integer projectId);
}
