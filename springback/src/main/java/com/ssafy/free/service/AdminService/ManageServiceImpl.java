package com.ssafy.free.service.AdminService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import com.ssafy.free.dto.Admin.AdminUser;
import com.ssafy.free.dto.Admin.Test;
import com.ssafy.free.dto.Admin.TestResponse;
import com.ssafy.free.dto.Admin.UrlAttribute;
import com.ssafy.free.repository.adminRepository.AdminUserRepository;
import com.ssafy.free.repository.adminRepository.TestRepository;
import com.ssafy.free.repository.adminRepository.UrlAttributeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
            LocalDate today = LocalDate.parse(LocalDate.now().toString(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));

            if (start.isEqual(today) || start.isBefore(today)) {
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
            // + testRepository.countByUrlBAndStatus(urlB, "진행중") > 0) {
            // return -2;
            // }

            Test test = new Test(admin_no, request.get("test_title").toString(), request.get("test_a").toString(),
                    request.get("test_b").toString(), start, end, Integer.parseInt(request.get("per_a").toString()),
                    Integer.parseInt(request.get("per_b").toString()), status, request.get("url_a").toString(), null);
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
    public Page<TestResponse> getTestList(String email, int page) {
        int admin_no = adminDao.findOneByEmail(email).getAdminNo();
        Pageable paging = PageRequest.of(page, 10, Sort.Direction.DESC, "testNo");
        Page<TestResponse> testList = testRepository.findAllByAdminNo(admin_no, paging);
        for (TestResponse test : testList) {
            List<UrlAttribute> urls = urlRepository.findByTestNo(test.getTest_no());
            test.setUrls(urls);
        }
        return testList;
    }

    @Override
    public Page<TestResponse> getTestListBefore(String email, int page) {
        int admin_no = adminDao.findOneByEmail(email).getAdminNo();
        Pageable paging = PageRequest.of(page, 10, Sort.Direction.DESC, "testNo");
        Page<TestResponse> testList = testRepository.findAllByAdminNoAndStatus(admin_no, "진행전", paging);
        for (TestResponse test : testList) {
            List<UrlAttribute> urls = urlRepository.findByTestNo(test.getTest_no());
            test.setUrls(urls);
        }
        return testList;
    }

    @Override
    public Page<TestResponse> getTestListProgress(String email, int page) {
        int admin_no = adminDao.findOneByEmail(email).getAdminNo();
        Pageable paging = PageRequest.of(page, 10, Sort.Direction.DESC, "testNo");
        Page<TestResponse> testList = testRepository.findAllByAdminNoAndStatus(admin_no, "진행중", paging);
        for (TestResponse test : testList) {
            List<UrlAttribute> urls = urlRepository.findByTestNo(test.getTest_no());
            test.setUrls(urls);
        }
        return testList;
    }

    @Override
    public Page<TestResponse> getTestListComplete(String email, int page) {
        int admin_no = adminDao.findOneByEmail(email).getAdminNo();
        Pageable paging = PageRequest.of(page, 10, Sort.Direction.DESC, "testNo");
        Page<TestResponse> testList = testRepository.findAllByAdminNoAndStatus(admin_no, "진행완료", paging);
        for (TestResponse test : testList) {
            List<UrlAttribute> urls = urlRepository.findByTestNo(test.getTest_no());
            test.setUrls(urls);
        }
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
