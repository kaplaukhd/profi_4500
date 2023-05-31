package com.example.myproject.config;

import com.example.myproject.entity.Article;
import com.example.myproject.entity.Role;
import com.example.myproject.entity.User;
import com.example.myproject.service.article.ArticleService;
import com.example.myproject.service.role.RoleService;
import com.example.myproject.service.user.UserService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class Initialize {

    private final UserService userService;
    private final ArticleService articleService;
    private final RoleService roleService;

    @PostConstruct
    private void createUserAndArticles() {
        List<Role> roles = new ArrayList<>();
        Role roleAdmin = new Role(1L, "ROLE_ADMIN");
        Role roleUser = new Role(2L, "ROLE_USER");
        roles.add(roleAdmin);
        roles.add(roleUser);
        roleService.saveAll(roles);


        User user = new User("Denis", "Kaplaukhd", "kaplaukhd@gmail.com", "admin", "user.jpg");
        user.getRoles().add(roleAdmin);

        Set<Article> list = new HashSet<>();
        for (int i = 0; i < 10; i++) {
            String imgUrl = i % 2 == 0 ? "http://gawain.ru/images/unesco/7_1_vrangel.jpg" : "";
            list.add(new Article("Xiaomi совсем недавно представила свой среднебюджетный смартфон Civi 3. На фоне конкурентов он выделяется своим элегантным дизайном и интересной палитрой расцветок корпуса, в которую входят зеленый, фиолетовый, золотой и серый цвет. \n" + "\n" + "В рамках празднования 100-летия Disney компания выпускает еще одну брендированную расцветку. В ней смартфон будет продаваться ограниченным тиражом.\n" + "\n" + "Пока что дизайн корпуса устройства держится в секрете — Xiaomi показала только коробку и интерфейс новинки. Последний преобразился до неузнаваемости — изменились не только обои, но и иконки приложений, а также дизайн внутри них.", "Xiaomi показала эксклюзивный смартфон к 100-летию Disney",  imgUrl));
        }

        articleService.saveAll(list);
        user.setArticles(list);
        userService.saveUser(user);

    }

}
