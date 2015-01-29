package com.epam.brest.courses.service.impl.DataFixture;

import com.epam.brest.courses.domain.Report;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by beast on 4.12.14. At 13.28
 */
public class ReportDataFixture {

    public static Report getNewReport(Long personId) {

        Report report = new Report();
        report.setPersonId(personId);
        report.setPersonFirstName("MockFirstName" + personId);
        report.setPersonLastName("MockLastName" + personId);
        report.setTimeTotal(10 + personId);

        return report;
    }

    public static List<Report> getSampleReportList() {
        List<Report> list = new ArrayList<>(3);
        list.add(ReportDataFixture.getNewReport(1L));
        list.add(ReportDataFixture.getNewReport(2L));
        list.add(ReportDataFixture.getNewReport(3L));

        return list;
    }
}
