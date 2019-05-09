package com.github.siilas.weatherfy.api.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import com.github.siilas.weatherfy.api.response.TrackSuggestion;

@Mapper
public interface TrackMapper {
	
	TrackMapper INSTANCE = Mappers.getMapper(TrackMapper.class); 
	
	@Mappings({
		@Mapping(target = "city", source = "city.name"),
		@Mapping(target =  "latitude", source = "city.coordinates.latitude"),
		@Mapping(target =  "longitude", source = "city.coordinates.longitude"),
		@Mapping(target =  "temperature", source = "city.temperature.value"),
		@Mapping(target =  "requestDate", source = "city.date"),
		@Mapping(target =  "genre", source = "genre"),
		@Mapping(target =  "tracks", source = "tracks.tracks")
	})
	TrackSuggestion convert(TrackSuggestion.TracksWrapper builder);

}
