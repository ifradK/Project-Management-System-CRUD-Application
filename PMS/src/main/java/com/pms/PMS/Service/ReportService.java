package com.pms.PMS.Service;

import com.pms.PMS.Entity.ProjectDtls;
import com.pms.PMS.Repository.ProjectRepository;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
//import java.util.Date;
import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ReportService {

    @Autowired
    private ProjectRepository projectRepository;

    public void exportReport(Date startDate, Date endDate) throws FileNotFoundException, JRException {

        String path = "E:\\Report";
        List<ProjectDtls> projectDtls = projectRepository.findByStartGreaterThanEqualAndEndLessThanEqual(startDate, endDate);
        File file = ResourceUtils.getFile("classpath:projectReport.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(projectDtls);
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("createdBy", "Jasper Report");
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
        JasperExportManager.exportReportToPdfFile(jasperPrint, path + "\\projectsList.pdf");
    }
}