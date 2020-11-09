package com.ssafy.free.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import com.ssafy.free.dto.Admin.*;
import com.ssafy.free.repository.AdminUserRepository;
import com.ssafy.free.repository.TestRepository;
import com.ssafy.free.repository.UrlAttributeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ManageServiceImpl implements ManageService {

    @Autowired
    private TestRepository testRepository;

    @Autowired
    private AdminUserRepository adminDao;

    @Autowired
    private UrlAttributeRepository urlRepository;

    @Override
    public int createTest(HashMap<String, Object> request) {
        int admin_no = adminDao.findOneByEmail(request.get("email").toString()).getAdminNo();

        try {
            LocalDate start = LocalDate.parse(request.get("start").toString(),
                    DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            LocalDate end = LocalDate.parse(request.get("end").toString(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));

            String status = "진행전";
            LocalDate today = LocalDate.now();

            if (start.isEqual(today)) {
                status = "진행중";
            }

            // url이 그 테스트기간 내에 일치하는게 있으면 막아
            String urlA = request.get("url_a").toString();
            // String urlB = request.get("url_b").toString();

            if (testRepository.countByUrlAAndStatus(urlA, "진행전")
                    + testRepository.countByUrlAAndStatus(urlA, "진행중") > 0) {
                return -1;
            }

            // if (testRepository.countByUrlBAndStatus(urlB, "진행전")
            //         + testRepository.countByUrlBAndStatus(urlB, "진행중") > 0) {
            //     return -2;
            // }

            Test test = new Test(admin_no, request.get("test_title").toString(), request.get("test_a").toString(),
                    null, start, end, Integer.parseInt(request.get("per_a").toString()),
                    Integer.parseInt(request.get("per_b").toString()), status, request.get("url_a").toString(),null);
            testRepository.save(test);

            // 타겟 URL 저장
            int testNo = testRepository.findByAdminNoAndTestTitle(admin_no, test.getTestTitle()).getTestNo();
            List<String> urls = (List<String>) request.get("urls");
            for (String url : urls) {
                UrlAttribute u = new UrlAttribute(testNo, url);
                urlRepository.save(u);
            }

            return 1;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int modifyTest(HashMap<String, Object> request) throws Exception {
        // 테스트 번호로 기존 테스트 불러오기
        try {
            Test test = testRepository.getOne(Integer.parseInt(request.get("test_no").toString()));
            test.setTestTitle(request.get("test_title").toString());
            test.setTestA(request.get("test_a").toString());
            test.setTestB(request.get("test_b").toString());

            LocalDate end = LocalDate.parse(request.get("end").toString(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            test.setEnd(end);
            testRepository.save(test);
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public List<TestResponse> getTestList(String email) {
        int admin_no = adminDao.findOneByEmail(email).getAdminNo();
        List<TestResponse> testList = testRepository.findAllByAdminNoOrderByTestNoDesc(admin_no);
        return testList;
    }

    @Override
    public List<TestResponse> getTestListBefore(String email) {
        int admin_no = adminDao.findOneByEmail(email).getAdminNo();
        List<TestResponse> testList = testRepository.findAllByAdminNoAndStatusOrderByTestNoDesc(admin_no, "진행전");
        return testList;
    }

    @Override
    public List<TestResponse> getTestListProgress(String email) {
        int admin_no = adminDao.findOneByEmail(email).getAdminNo();
        List<TestResponse> testList = testRepository.findAllByAdminNoAndStatusOrderByTestNoDesc(admin_no, "진행중");
        return testList;
    }

    @Override
    public List<TestResponse> getTestListComplete(String email) {
        int admin_no = adminDao.findOneByEmail(email).getAdminNo();
        List<TestResponse> testList = testRepository.findAllByAdminNoAndStatusOrderByTestNoDesc(admin_no, "진행완료");
        return testList;
    }

    @Override
    public int deleteTest(int test_no) {
        try {
            testRepository.deleteById(test_no);
            ;
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;

    }

    @Override
    public String login(HashMap<String, Object> request) {
        // id pw 일치하는 UserSample이 있는지 확인
        try {
            Optional<AdminUser> user = adminDao.findByEmailAndPw(request.get("id").toString(),
                    request.get("pw").toString());
            if (user.isPresent()) {
                return user.get().getEmail();
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

}
