package com.dizang.common;

import com.dizang.entity.Master;
import com.dizang.entity.Teaching;
import com.dizang.mapper.MasterMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class TeachingEnricher {

    private final MasterMapper masterMapper;

    public void fillMasterName(List<Teaching> teachings) {
        if (teachings == null || teachings.isEmpty()) {
            return;
        }
        Set<Long> masterIds = teachings.stream()
                .map(Teaching::getMasterId)
                .filter(Objects::nonNull)
                .collect(Collectors.toSet());
        if (masterIds.isEmpty()) {
            return;
        }
        Map<Long, String> nameMap = masterMapper.selectBatchIds(masterIds).stream()
                .collect(Collectors.toMap(Master::getId, Master::getName, (a, b) -> a));
        teachings.forEach(t -> t.setMasterName(nameMap.get(t.getMasterId())));
    }

    public void fillMasterName(Teaching teaching) {
        if (teaching == null || teaching.getMasterId() == null) {
            return;
        }
        Master master = masterMapper.selectById(teaching.getMasterId());
        if (master != null) {
            teaching.setMasterName(master.getName());
        }
    }
}
