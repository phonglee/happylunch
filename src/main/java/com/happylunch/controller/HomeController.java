package com.happylunch.controller;

import com.happylunch.entity.Category;
import com.happylunch.entity.Customer;
import com.happylunch.entity.CustomerOrder;
import com.happylunch.entity.Product;
import com.happylunch.repository.CategoryRepository;
import com.happylunch.repository.CustomerRepository;
import com.happylunch.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.util.Date;
import java.sql.DriverManager;
import java.sql.SQLException;

@Controller
@RequestMapping("/")
public class HomeController {

    private CategoryRepository categoryRepository;
    private ProductRepository productRepository;
    private CustomerRepository customerRepository;

    @Autowired
    public HomeController(CategoryRepository categoryRepository, ProductRepository productRepository, CustomerRepository customerRepository) {
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
        this.customerRepository = customerRepository;
    }

    @RequestMapping("/")
    public String home(Model model) {
        model.addAttribute("customerOrder", new CustomerOrder());
        model.addAttribute("products", productRepository.findAllAvailable());
        return "index";
    }

    @RequestMapping("/administration")
    public String admin(Model model) {
        return "admin";
    }

    @RequestMapping("/pl")
    public String pl(Model model) {
        return "pl";
    }

    @RequestMapping("/init")
    public String initData(Model model) {
        initProduct();
        return "redirect:/product/view";
    }

    @RequestMapping("/addcategory")
    public String addCategory(Model model) {
        model.addAttribute("category", new Category());
        return "category/addcategory";
    }

    @RequestMapping(value = "/addcategory", method = RequestMethod.POST)
    public String addCategory(@ModelAttribute @Valid Category category, Model model, BindingResult result) {
        model.addAttribute("category", category);
        if(!result.hasErrors()) {
            categoryRepository.save(category);
        }
        return "category/viewcategory";
    }
    private void initProduct() {
        Category category = initCategory("rice");
        Category noodle = initCategory("noodle");
        Date today = new Date();
        Product product = productRepository.findById(new Integer(1));
        if (null == product) {
            product = new Product("MS1 Thịt Luộc Cà Pháo", "Thịt luộc cà pháo + Canh cua + Cơm + Tráng miệng", new BigDecimal("25000"), "../assets/images/food/01.jpg", today, category);
            productRepository.save(product);

            product = new Product("MS2 Cá Basa Kho Tiêu", "Cá basa kho tiêu + Cơm + Rau + Tráng miệng", new BigDecimal("25000"), "../assets/images/food/02.jpg", today, category);
            productRepository.save(product);

            product = new Product("MS3 Chim Cút Rôti", "Chim cút rôti + Cơm + Rau + Tráng miệng", new BigDecimal("25000"), "../assets/images/food/03.jpg", today, category);
            productRepository.save(product);

            product = new Product("MS4 Thịt Kho Trứng", "Thịt kho trứng + Cơm + Rau + Tráng miệng", new BigDecimal("25000"), "../assets/images/food/04.jpg", today, category);
            productRepository.save(product);

            product = new Product("MS5 Sườn Non Chua Ngọt", "Sườn non chua ngọt + Rau + Tráng miệng", new BigDecimal("25000"), "../assets/images/food/05.jpg", today, category);
            productRepository.save(product);

            product = new Product("MS6 Bún Bò", "Bún bò + Rau + Tráng miệng", new BigDecimal("25000"), "../assets/images/food/06.jpg", today, noodle);
            productRepository.save(product);

            product = new Product("MS7 Chả Cá Viên Trứng Cút Sốt Cà", "Chả cả viên trứng cút sốt cà + Cơm + Rau + Tráng miệng", new BigDecimal("25000"), "../assets/images/food/07.jpg", today, category);
            productRepository.save(product);

            product = new Product("MS8 Hủ Tiếu Mì Chay", "Hủ tiếu mì chay + Rau + Tráng miệng", new BigDecimal("25000"), "../assets/images/food/08.jpg", today, noodle);
            productRepository.save(product);

            product = new Product("MS9 Gà Chiên Sốt Chanh", "Gà chiên sốt chanh + Cơm + Rau + Tráng miệng", new BigDecimal("25000"), "../assets/images/food/09.jpg", today, category);
            productRepository.save(product);

            product = new Product("MS10 Cá Trê Chiên Mắm Gừng", "Cá trê chiên mắm gừng + Cơm + Rau + Tráng miệng", new BigDecimal("25000"), "../assets/images/food/10.jpg", today, category);
            productRepository.save(product);

            product = new Product("MS11 Cá Diêu Hồng Chưng Tương", "Cá diêu hồng chưng tương + Rau + Tráng miệng", new BigDecimal("25000"), "../assets/images/food/11.jpg", today, category);
            productRepository.save(product);

            product = new Product("MS12 Vịt Kho Gừng", "Vịt kho gừng + Cơm + Rau + Tráng miệng", new BigDecimal("25000"), "../assets/images/food/12.jpg", today, category);
            productRepository.save(product);

            product = new Product("MS13 Bò Nấu Lagu", "Bò nấu lagu + Cơm + Rau + Tráng miệng", new BigDecimal("25000"), "../assets/images/food/13.jpg", today, category);
            productRepository.save(product);

            product = new Product("MS14 Gà Kho Nghệ", "Gà kho nghệ + Cơm + Rau + Tráng miệng", new BigDecimal("25000"), "../assets/images/food/14.jpg", today, category);
            productRepository.save(product);

            product = new Product("MS15 Đậu Hũ Om Trứng", "Đậu hũ om trứng + Cơm + Rau + Tráng miệng", new BigDecimal("25000"), "../assets/images/food/15.jpg", today, category);
            productRepository.save(product);

            product = new Product("MS16 Thịt Bọc Sả Chiên", "Thịt bọc sả chiên + Cơm + Rau + Tráng miệng", new BigDecimal("25000"), "../assets/images/food/16.jpg", today, category);
            productRepository.save(product);
        }
    }

    private Category initCategory(String name) {
        Category category = categoryRepository.findByName(name);
        if (null == category) {
            category = new Category();
            category.setName(name);
            categoryRepository.save(category);
        }
        return category;
    }


    private static Connection getConnection() throws URISyntaxException, SQLException {
        URI dbUri;
        if (System.getenv("DATABASE_URL") != null) {
            dbUri = new URI(System.getenv("DATABASE_URL"));
        } else {
            String DATABASE_URL = "postgres://tgfashion:tgfashion@10.189.222.139:5432/tgfashion";
            dbUri = new URI(DATABASE_URL);
        }

        String username = dbUri.getUserInfo().split(":")[0];
        String password = dbUri.getUserInfo().split(":")[1];
        String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + ':'
                + dbUri.getPort() + dbUri.getPath()
                + "?sslmode=require";
        return DriverManager.getConnection(dbUrl, username, password);
    }
}
