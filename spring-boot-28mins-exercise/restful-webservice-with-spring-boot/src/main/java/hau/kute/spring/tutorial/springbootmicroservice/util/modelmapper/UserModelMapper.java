package hau.kute.spring.tutorial.springbootmicroservice.util.modelmapper;

import hau.kute.spring.tutorial.springbootmicroservice.data.UserEntity;
import hau.kute.spring.tutorial.springbootmicroservice.shared.UserDTO;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

public class UserModelMapper {

	public static UserEntity parseFromDTOToEntity(UserDTO userDTO) {

		UserEntity userEntity = modelMapper.map(userDTO, UserEntity.class);
		return userEntity;
	}

	public static UserDTO parseFromEntityToDTO(UserEntity userEntity) {

		UserDTO userDTO = modelMapper.map(userEntity, UserDTO.class);
		return userDTO;
	}

	private static final ModelMapper modelMapper = new ModelMapper();

	static {
		modelMapper.getConfiguration().setMatchingStrategy(
						MatchingStrategies.STRICT);
	}

}
