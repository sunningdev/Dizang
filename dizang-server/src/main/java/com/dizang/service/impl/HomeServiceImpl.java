package com.dizang.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.dizang.common.TeachingEnricher;
import com.dizang.entity.Banner;
import com.dizang.entity.Teaching;
import com.dizang.entity.Topic;
import com.dizang.mapper.BannerMapper;
import com.dizang.mapper.TeachingMapper;
import com.dizang.mapper.TopicMapper;
import com.dizang.service.HomeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class HomeServiceImpl implements HomeService {

    private final BannerMapper bannerMapper;
    private final TopicMapper topicMapper;
    private final TeachingMapper teachingMapper;
    private final TeachingEnricher teachingEnricher;

    @Override
    public Map<String, Object> getHomeData() {
        Map<String, Object> data = new HashMap<>();

        List<Banner> banners = bannerMapper.selectList(
            new LambdaQueryWrapper<Banner>()
                .eq(Banner::getStatus, "0")
                .eq(Banner::getDelFlag, "0")
                .orderByAsc(Banner::getSortOrder)
        );

        List<Topic> topics = topicMapper.selectList(
            new LambdaQueryWrapper<Topic>()
                .eq(Topic::getStatus, "0")
                .eq(Topic::getDelFlag, "0")
                .orderByAsc(Topic::getSortOrder)
        );

        List<Teaching> latestTeachings = teachingMapper.selectList(
            new LambdaQueryWrapper<Teaching>()
                .eq(Teaching::getStatus, "0")
                .eq(Teaching::getDelFlag, "0")
                .orderByDesc(Teaching::getPublishTime)
                .last("LIMIT 5")
        );

        teachingEnricher.fillMasterName(latestTeachings);

        data.put("banners", banners);
        data.put("topics", topics);
        data.put("latestTeachings", latestTeachings);
        return data;
    }
}