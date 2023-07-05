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
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ReportService {

    @Autowired
    private ProjectRepository projectRepository;

//    public String exportReport(String reportFormat) throws FileNotFoundException, JRException {
//    public void exportReport() throws FileNotFoundException, JRException {

    public void exportReport(Date startDate, Date endDate) throws FileNotFoundException, JRException {

        String path = "E:\\Report";
        List<ProjectDtls> projectDtls = projectRepository.findByStartGreaterThanEqualAndEndLessThanEqual(startDate, endDate);
//        List<ProjectDtls> projectDtls = projectRepository.findAll();

        File file = ResourceUtils.getFile("classpath:projectReport.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(projectDtls);
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("createdBy", "Jasper Report");
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);

        JasperExportManager.exportReportToPdfFile(jasperPrint, path + "\\projectsList.pdf");
    }
}

//        if(reportFormat.equalsIgnoreCase("pdf")) {
//            JasperExportManager.exportReportToPdfFile(jasperPrint, path + "\\projectsList.pdf");
//        }