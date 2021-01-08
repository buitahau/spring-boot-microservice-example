package hau.kute.spring.tutorial.springbootmicroservice.util.modelmapper;

import hau.kute.spring.tutorial.springbootmicroservice.data.UserEntity;
import hau.kute.spring.tutorial.springbootmicroservice.shared.UserDTO;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

public class UserModelMapper {

	public static UserEntity parseFromDTOToEntity(UserDTO user) {

		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies
						.STRICT);
		UserEntity userEntity = modelMapper.map(user, UserEntity.class);
		return userEntity;
	}

}
