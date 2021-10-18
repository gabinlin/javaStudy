package top.gabin.springBoot.controller;

import org.springframework.context.annotation.Lazy;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.gabin.springBoot.mulDataSource.DataSource;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/jdbc")
public class JDBCController {

    @Lazy
    @Resource
    private JdbcTemplate jdbcTemplate;

    @DataSource
    @GetMapping("/list")
    public List<?> list() {
        List<Map<String, Object>> list = jdbcTemplate.queryForList("select * from hr_probation_main limit 10");
        System.out.println(list);
        System.out.println(list.size());
        return list;
    }

    @DataSource
    @GetMapping("/list2")
    public List<?> list2() {
        List<Map<String, Object>> list = jdbcTemplate.queryForList("select * from flyway_schema_history limit 10");
        System.out.println(list);
        System.out.println(list.size());
        return list;
    }

    @GetMapping("/insert")
    public String insert() {
        jdbcTemplate.execute("INSERT INTO `hr_probation_main` (`id`, `emp_number`, `emp_name`, `dept_path`, `station`, `job_level`, `is_manager`, `join_time`, `parent_n1`, `parent_n1_name`, `double_superior_number`, `double_superior_name`, `parent_n2`, `parent_n2_name`, `suggest_n1`, `examine_month`, `begin_time`, `end_time`, `kpi_score`, `attitude_score`, `score`, `status`, `deleted`, `create_time`, `update_time`, `create_by`, `update_by`, `hr_emp_number`, `process_instance_id`, `status_desc`, `is_finish_course_study`, `task_node`, `task_node_assignee`)\n" +
                "VALUES\n" +
                "\t(1394, '2017020087', '范晓铠', '20200000,20203738,20110001,20112001,20112017,,', '招聘专员', 'C1', 0, '2017-02-13 00:00:00', '2013050088', '郑锦滨', '', '', '', '', '', 6, '2017-02-13 00:00:00', '2017-05-01 00:00:00', 0.00, 0.00, 0.00, 0, 0, '2020-10-12 20:38:34', '2020-12-28 17:40:56', '系统自动更新', '2019110864', '2008020187', '1321366827883823104', '', 0, '', '');\n");
        return "success";
    }

}
