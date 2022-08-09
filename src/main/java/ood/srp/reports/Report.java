package ood.srp.reports;

import java.util.function.Predicate;

public interface Report {
    String generate(Predicate<Employee> filter);
}