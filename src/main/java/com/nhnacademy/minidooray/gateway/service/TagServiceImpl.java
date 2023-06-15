package com.nhnacademy.minidooray.gateway.service;

import com.nhnacademy.minidooray.gateway.adopter.TagAdopter;
import com.nhnacademy.minidooray.gateway.adopter.TaskAdopter;
import com.nhnacademy.minidooray.gateway.domain.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TagServiceImpl implements TagService {
    private final TagAdopter tagAdopter;
    public List<Tag> getTags(Integer projectId){
       return tagAdopter.getTags(projectId);
    }
}
