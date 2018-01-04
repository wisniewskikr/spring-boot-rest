package pl.kwi.springboot.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import pl.kwi.springboot.db.entities.UserEntity;
import pl.kwi.springboot.db.repositories.UserRepository;

@RestController
@RequestMapping("/user")
@Api(value="user", description="User operations")
public class UserController {
	
	
	@Autowired
	private UserRepository userRepository;
	
	private static final Gson gson = new Gson();
	
	
	@RequestMapping(value = "/list", method= RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "View a list of available users",response = List.class)
    public ResponseEntity<List<UserEntity>> getUsers() {
		return new ResponseEntity<List<UserEntity>>(userRepository.findAll(), HttpStatus.OK);
    }
	
	@RequestMapping(value = "/show/{id}", method= RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Search a user with an ID",response = UserEntity.class)    
    public ResponseEntity<UserEntity> showUser(@ApiParam(value = "User Id to show", defaultValue = "1") @PathVariable Long id){
	 	return new ResponseEntity<UserEntity>(userRepository.findOne(id), HttpStatus.OK);
    }
	
	@RequestMapping(value = "/add", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Add an user")	
    public ResponseEntity<String> addUser(@RequestBody @ApiParam(name = "User Entity", value = "User to be added") UserEntity user){
		userRepository.save(user);
        return new ResponseEntity<String>(gson.toJson("User saved successfully"), HttpStatus.OK);
    }
	
	@RequestMapping(value = "/update", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Update an user")    
    public ResponseEntity<String> updateUser(@RequestBody @ApiParam(name = "User Entity", value = "User to be updated") UserEntity user){  
		userRepository.save(user);
        return new ResponseEntity<String>(gson.toJson("User updated successfully"), HttpStatus.OK);
    }
	
	@RequestMapping(value="/delete/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Delete an user")    
    public ResponseEntity<String> delete(@ApiParam(value = "User Id to delete", defaultValue = "1") @PathVariable Long id){
		userRepository.delete(id);
        return new ResponseEntity<String>(gson.toJson("User deleted successfully"), HttpStatus.OK);
 
    }

}