package com.nhnacademy.minidooray.gateway.service;

import com.nhnacademy.minidooray.gateway.adopter.MilestoneAdopter;
import com.nhnacademy.minidooray.gateway.domain.Milestone;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MilestoneServiceImpl implements MilestoneService {
    private final MilestoneAdopter milestoneAdopter;
    @Override
    public List<Milestone> getMilestones(Integer projectId) {
       return milestoneAdopter.getMilestones(projectId);
    }
}
