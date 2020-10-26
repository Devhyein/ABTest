package com.ssafy.free.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;

import com.ssafy.free.dto.AdminUser;
import com.ssafy.free.dto.Test;
import com.ssafy.free.repository.AdminUserRepository;
import com.ssafy.free.repository.TestRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ManageServiceImpl implements ManageService {

    @Autowired
    private TestRepository testRepository;

    @Autowired
    private AdminUserRepository adminDao;

    @Override
    public int createTest(HashMap<String, Object> request) {

        // AdminUser user = new AdminUser("test", "password");
        // adminDao.save(user);

        System.out.println("EMAIL : " + request.get("email").toString());
        System.out.println("FIND ALL : " + adminDao.findAll());
        System.out.println("FIND BY EMAIL : " + adminDao.findOneByEmail(request.get("email").toString()));
        System.out.println("ADMIN NO : " + adminDao.findOneByEmail(request.get("email").toString()).getAdminNo());
        int admin_no = adminDao.findOneByEmail(request.get("email").toString()).getAdminNo();

        // 혹시 시분초까지 필요하면 이걸 사용 LocalDateTime
        // DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd
        // HH:mm:ss.SSS");
        try {
            System.out.println("START STRING : " + request.get("start").toString());
            LocalDate start = LocalDate.parse(request.get("start").toString(),
                    DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            LocalDate end = LocalDate.parse(request.get("end").toString(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            System.out.println("START LOCALDATETIME: " + start);

            Test test = new Test(admin_no, request.get("test_title").toString(), request.get("test_a").toString(),
                    request.get("test_b").toString(), start, end, Integer.parseInt(request.get("per_a").toString()),
                    Integer.parseInt(request.get("per_b").toString()), request.get("status").toString());
            testRepository.save(test);

            return 1;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public List<Test> getTestList(String email) {
        int admin_no = adminDao.findOneByEmail(email).getAdminNo();
        System.out.println("ADMIN NO : " + admin_no);
        List<Test> testList = testRepository.findAllByAdminNo(admin_no);
        return testList;
    }

}
