package org.restaurant.orderservice.bootstrap;

import aj.org.objectweb.asm.TypeReference;
import org.restaurant.orderservice.features.menu.internal.model.Menu;
import org.restaurant.orderservice.features.user.internal.model.Users;
import org.restaurant.orderservice.features.menu.internal.repository.MenuDao;
import org.restaurant.orderservice.features.user.internal.repository.UserDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import tools.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;

@Component
@Profile("dev")

public class DataLoaderDev implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(DataLoaderDev.class);
    private final UserDao userRepository;
    private final MenuDao menuDao;
    private final ObjectMapper objectMapper;

    public DataLoaderDev(UserDao userRepository, MenuDao menuDao, ObjectMapper objectMapper) {

        this.userRepository = userRepository;
        this.menuDao = menuDao;
        this.objectMapper = objectMapper;
    }

    @Override
    public void run(String... args) throws Exception {
        if(menuDao.count() ==0){
            try (InputStream menuInputStream = TypeReference.class.getResourceAsStream("/data/menu.json")){

                Menu menu = objectMapper.readValue(menuInputStream, Menu.class);

                log.info("Saving {} menu data read from JSON.", menu.menuList().size());

                menuDao.saveAll(menu.menuList());
            }catch(IOException e){
                throw new RuntimeException("Reading from JSON has failed", e);
            }
        }
        if(userRepository.count() ==0){
            try (InputStream userInputStream = TypeReference.class.getResourceAsStream("/data/users.json")){

                Users users = objectMapper.readValue(userInputStream, Users.class);

                log.info("Saving {} user data read from JSON.", users.userList().size());

                userRepository.saveAll(users.userList());
            }catch(IOException e){
                throw new RuntimeException("Reading from JSON has failed", e);
            }
        }
        else{
            log.info("Saving data from JSON has failed as db has been already populated");
        }
    }
}
