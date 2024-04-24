package com.ispirer.hacker.mapper;

import com.ispirer.hacker.dto.news.News;
import com.ispirer.hacker.dto.news.NewsDetails;
import com.ispirer.hacker.dto.news.NewsWebClientResponse;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface NewsMapper {
    News fromNewsWebClientResponseToNews(NewsWebClientResponse newsWebClientResponse);
    NewsDetails fromNewsDetailsWebClientResponseToNews(NewsWebClientResponse newsWebClientResponse);
}