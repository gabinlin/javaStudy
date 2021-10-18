package top.gabin.springBoot.filter;

import com.alibaba.druid.filter.stat.StatFilter;
import org.springframework.stereotype.Component;

@Component("stat")
public class DruidStatFilter extends StatFilter {
}
