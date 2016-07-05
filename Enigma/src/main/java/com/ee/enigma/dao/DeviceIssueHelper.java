package com.ee.enigma.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.ee.enigma.common.CommonUtils;
import com.ee.enigma.common.Constants;
import com.ee.enigma.model.DeviceInfo;
import com.ee.enigma.model.DeviceIssueInfo;
import com.ee.enigma.model.ReportInfo;
import com.ee.enigma.model.ReportResultInfo;
import com.ee.enigma.model.UserActivity;

public class DeviceIssueHelper
{
  public List<ReportInfo> buildDeviceIssueTrendList(List<DeviceIssueInfo> deviceIssueInfos)
  {
    DeviceIssueInfo deviceIssueInfo = null;
    List<ReportInfo> reportInfoList = new ArrayList<ReportInfo>();
    ReportInfo reportInfo = null;
    if (deviceIssueInfos != null && deviceIssueInfos.size() > 0)
    {
      for (int i = 0; i < deviceIssueInfos.size(); i++)
      {
         deviceIssueInfo = deviceIssueInfos.get(i);
          reportInfo = new ReportInfo();
          reportInfo.setDeviceId(deviceIssueInfo.getDeviceInfo().getDeviceId());
          reportInfo.setDeviceName(deviceIssueInfo.getDeviceInfo().getDeviceName());
          reportInfo.setDeviceAvailability(deviceIssueInfo.getDeviceInfo().getDeviceAvailability());
          reportInfo.setManufacturer(deviceIssueInfo.getDeviceInfo().getManufacturer());
          reportInfo.setOS(deviceIssueInfo.getDeviceInfo().getOS());
          reportInfo.setOSVersion(deviceIssueInfo.getDeviceInfo().getOSVersion());
          reportInfo.setIssueTime(deviceIssueInfo.getIssueTime());
          if(reportInfo.getIssueByAdmin()!=null && reportInfo.getIssueByAdmin())
          {
            reportInfo.setIssuedBy(Constants.ISSUED_BY_ADMIN);
          }
          else
          {
            reportInfo.setIssuedBy(Constants.ISSUED_BY_SYSTEM);
          }
          reportInfo.setSubmitTime(deviceIssueInfo.getSubmitTime());
          reportInfoList.add(reportInfo);
      }
    }
    return reportInfoList;
  }  
  
  public List<ReportInfo> buildDeviceSubmitTrendList(List<DeviceIssueInfo> deviceIssueInfos)
  {
    DeviceIssueInfo deviceIssueInfo = null;
    List<ReportInfo> reportInfoList = new ArrayList<ReportInfo>();
    ReportInfo reportInfo = null;
    if (deviceIssueInfos != null && deviceIssueInfos.size() > 0)
    {
      for (int i = 0; i < deviceIssueInfos.size(); i++)
      {
         deviceIssueInfo = deviceIssueInfos.get(i);
          reportInfo = new ReportInfo();
          reportInfo.setDeviceId(deviceIssueInfo.getDeviceInfo().getDeviceId());
          reportInfo.setDeviceName(deviceIssueInfo.getDeviceInfo().getDeviceName());
          reportInfo.setDeviceAvailability(deviceIssueInfo.getDeviceInfo().getDeviceAvailability());
          reportInfo.setManufacturer(deviceIssueInfo.getDeviceInfo().getManufacturer());
          reportInfo.setOS(deviceIssueInfo.getDeviceInfo().getOS());
          reportInfo.setOSVersion(deviceIssueInfo.getDeviceInfo().getOSVersion());
          reportInfo.setIssueTime(deviceIssueInfo.getIssueTime());
          if(reportInfo.getSubmitByAdmin()!=null && reportInfo.getSubmitByAdmin())
          {
            reportInfo.setIssuedBy(Constants.SUBMITTED_BY_ADMIN);
          }
          else
          {
            reportInfo.setIssuedBy(Constants.SUBMITTED_BY_SYSTEM);
          }
          reportInfo.setSubmitTime(deviceIssueInfo.getSubmitTime());
          reportInfoList.add(reportInfo);
      }
    }
    return reportInfoList;
  }  
  
 
 public ReportInfo buildDeviceReportAvailability(List<DeviceInfo> deviceInfoList)
  {
    DeviceInfo deviceInfo = null;
     int totalDevicesCount=0;
    int availableDevices=0;
    int issuedDevices=0;
    ReportInfo reportInfo = null;
    for (int i = 0; i < deviceInfoList.size(); i++)
    {
        deviceInfo = deviceInfoList.get(i);
        totalDevicesCount++;
        if(Constants.DEVICE_INFO_ADMIN_AVAILABLE.equals(deviceInfo.getDeviceAvailability()))
        {
          availableDevices++;
        }
        if(Constants.DEVICE_INFO_ISSUED_TO_USER.equals(deviceInfo.getDeviceAvailability()))
        {
          issuedDevices++;
        }
     }
    reportInfo=new ReportInfo();
    reportInfo.setTotalDevices(totalDevicesCount);
    reportInfo.setAvailableDevices(availableDevices);
    reportInfo.setIssuedDevices(issuedDevices);
    return reportInfo;
  }  
  
 public List<ReportInfo> buildDeviceIssueReportListByStatus(List<DeviceInfo> deviceInfoList)
 {
   //DeviceIssueInfo deviceIssueInfo = null;
   DeviceInfo deviceInfo = null;
   //List<String> deviceInfoListByissueId = new ArrayList<String>();
   List<ReportInfo> reportInfoList = new ArrayList<ReportInfo>();
   ReportInfo reportInfo = null;
   /*if (deviceIssueInfos != null && deviceIssueInfos.size() > 0)
   {
     for (int i = 0; i < deviceIssueInfos.size(); i++)
     {
       deviceIssueInfo = deviceIssueInfos.get(i);
       if (!deviceInfoListByissueId.contains(deviceIssueInfo.getDeviceId()))
       {
         deviceInfoListByissueId.add(deviceIssueInfo.getDeviceId());
         reportInfo = new ReportInfo();
         reportInfo.setDeviceId(deviceIssueInfo.getDeviceInfo().getDeviceId());
         reportInfo.setDeviceName(deviceIssueInfo.getDeviceInfo().getDeviceName());
         reportInfo.setIsAdminApproved(deviceIssueInfo.getDeviceInfo().getIsAdminApproved());
         reportInfo.setManufacturer(deviceIssueInfo.getDeviceInfo().getManufacturer());
         reportInfo.setOS(deviceIssueInfo.getDeviceInfo().getOS());
         reportInfo.setOSVersion(deviceIssueInfo.getDeviceInfo().getOSVersion());
         reportInfoList.add(reportInfo);
       }
     }
   }*/
   for (int i = 0; i < deviceInfoList.size(); i++)
   {
     deviceInfo = deviceInfoList.get(i);
       reportInfo = new ReportInfo();
       reportInfo.setDeviceId(deviceInfo.getDeviceId());
       reportInfo.setDeviceName(deviceInfo.getDeviceName());
       reportInfo.setDeviceAvailability(deviceInfo.getDeviceAvailability());
        reportInfo.setManufacturer(deviceInfo.getManufacturer());
       reportInfo.setOS(deviceInfo.getOS());
       reportInfo.setOSVersion(deviceInfo.getOSVersion());
       reportInfoList.add(reportInfo);
   }
   return reportInfoList;
 }  
 
  public JSONObject buildJSONObjectForDateWiseDeviceReport(List<DeviceIssueInfo> deviceIssueInfos)
 // public List<DeviceIssueInfo> buildJSONObjectForDateWiseDeviceReport(List<DeviceIssueInfo> deviceIssueInfos)
  {
    JSONObject deviceJson = new JSONObject();
    ReportResultInfo reportResultInfo = null;
    List<ReportResultInfo> reportResultInfoList = new ArrayList<ReportResultInfo>();
    List<ReportInfo> reportInfoList = null;
    ReportInfo reportInfo = null;
    JSONArray userActityJsonArray = null;
    JSONArray issueIdJsonArray = null;
    String deviceId = "";
    JSONObject issueIdJson = null;
    JSONObject userActivityJson = null;
    DeviceIssueInfo deviceIssueInfo = null;
    if (deviceIssueInfos != null && deviceIssueInfos.size() > 0)
    {
      UserActivity userActivity = null;
      issueIdJsonArray = new JSONArray();
      for (int i = 0; i < deviceIssueInfos.size(); i++)
      {
        deviceIssueInfo = deviceIssueInfos.get(i);
        deviceId = deviceIssueInfo.getDeviceId();
        issueIdJson = new JSONObject();
        issueIdJson.put("issueId", deviceIssueInfo.getIssueId());
        issueIdJson.put("issueTime", CommonUtils.getDayBeginTime(deviceIssueInfo.getIssueTime()));
        issueIdJson.put("submitTime", CommonUtils.getDayEndTime(deviceIssueInfo.getSubmitTime()));
        userActityJsonArray = null;
        if (deviceIssueInfo.getUserActivity() != null)
        {
          Iterator<UserActivity> iterator = deviceIssueInfo.getUserActivity().iterator();
          userActityJsonArray = new JSONArray();
          reportInfoList = new ArrayList<ReportInfo>();
          while (iterator.hasNext())
          {
            userActivity = iterator.next();
            reportInfo = new ReportInfo();
            reportInfo.setLoginTime(userActivity.getLoginTime());
            reportInfo.setLogoutTime(userActivity.getLogoutTime());
            reportInfo.setUserName(deviceIssueInfo.getUserInfo().getUserName());
            reportInfoList.add(reportInfo);
          }
          if (reportInfoList != null && reportInfoList.size() > 0)
          {
            reportResultInfo = null;
            for (int j = 0; j < reportInfoList.size(); j++)
            {
              reportInfo = reportInfoList.get(j);
              if (j == 0)
              {
                reportResultInfo = new ReportResultInfo();
                reportResultInfo.setReportInfoNext(reportInfo);
                reportInfo = new ReportInfo();
                reportInfo.setLoginTime(deviceIssueInfo.getIssueTime());
                reportInfo.setLogoutTime(userActivity.getLogoutTime());
                reportInfo.setUserName(deviceIssueInfo.getUserInfo().getUserName());
                reportInfo.setFromTable("DIINFO");
                reportResultInfo.setReportInfo(reportInfo);
              }
              else if (j == (reportInfoList.size() - 1))
              {
                reportResultInfo = new ReportResultInfo();
                reportResultInfo.setReportInfo(reportInfoList.get(j));
                reportInfo = new ReportInfo();
                reportInfo.setLoginTime(null);
                reportInfo.setLogoutTime(deviceIssueInfo.getSubmitTime());
                reportInfo.setUserName(deviceIssueInfo.getUserInfo().getUserName());
                reportInfo.setFromTable("DIINFO");
                reportResultInfo.setReportInfoNext(reportInfo);
              }
              else
              {
                reportResultInfo.setReportInfo(reportInfoList.get(j - 1));
                reportResultInfo.setReportInfoNext(reportInfoList.get(j));
              }
              reportResultInfoList.add(reportResultInfo);
            }
            
          }
          for(int k=0;k<reportResultInfoList.size();k++)
          {
            reportResultInfo=reportResultInfoList.get(k);
           
            userActivityJson=null;
            if(k==0)
            {
              userActivityJson=new JSONObject();
              userActivityJson.put("userName", deviceIssueInfo.getUserInfo().getUserName());
              userActivityJson.put("inTime", reportResultInfo.getReportInfo().getLoginTime());
              userActivityJson.put("outTime","NA");
              userActivityJson.put("useStatus", "Issued");
              userActivityJson.put("duration", "NA");
              userActityJsonArray.add(userActivityJson);
              
              //Next
              userActivityJson=new JSONObject();
              userActivityJson.put("userName", deviceIssueInfo.getUserInfo().getUserName());
              userActivityJson.put("inTime", reportResultInfo.getReportInfo().getLogoutTime());
              userActivityJson.put("outTime", reportResultInfo.getReportInfoNext().getLoginTime());
              userActivityJson.put("duration", CommonUtils.getTimeDiffernce(reportResultInfo.getReportInfo().getLogoutTime(), reportResultInfo.getReportInfoNext().getLoginTime()));
              userActivityJson.put("useStatus", "Idle");
              userActityJsonArray.add(userActivityJson);
              
            }
            else if(k==(reportResultInfoList.size()-1))
            {
              userActivityJson=new JSONObject();
              userActivityJson.put("userName", deviceIssueInfo.getUserInfo().getUserName());
              userActivityJson.put("inTime", reportResultInfo.getReportInfo().getLoginTime());
              userActivityJson.put("outTime", reportResultInfo.getReportInfo().getLogoutTime());
              userActivityJson.put("duration", CommonUtils.getTimeDiffernce(userActivity.getLoginTime(), userActivity.getLogoutTime()));
              userActivityJson.put("useStatus", "Active");
              userActityJsonArray.add(userActivityJson);
              
              //Next
              userActivityJson=new JSONObject();
              userActivityJson.put("userName", deviceIssueInfo.getUserInfo().getUserName());
              userActivityJson.put("inTime", reportResultInfo.getReportInfo().getLogoutTime());
              userActivityJson.put("outTime", reportResultInfo.getReportInfoNext().getLoginTime());
              userActivityJson.put("duration", CommonUtils.getTimeDiffernce(reportResultInfo.getReportInfo().getLogoutTime(), reportResultInfo.getReportInfoNext().getLoginTime()));
              userActivityJson.put("useStatus", "Idle");
              userActityJsonArray.add(userActivityJson);
              //Last
              userActivityJson=new JSONObject();
              userActivityJson.put("userName", deviceIssueInfo.getUserInfo().getUserName());
              userActivityJson.put("inTime", "NA");
              userActivityJson.put("outTime", reportResultInfo.getReportInfoNext().getLogoutTime());
              userActivityJson.put("duration", "NA");
              userActivityJson.put("useStatus", "Submitted");
              userActityJsonArray.add(userActivityJson);
            }
            else
            {
              userActivityJson=new JSONObject();
              userActivityJson.put("userName", deviceIssueInfo.getUserInfo().getUserName());
              userActivityJson.put("inTime", reportResultInfo.getReportInfo().getLoginTime());
              userActivityJson.put("outTime", reportResultInfo.getReportInfo().getLogoutTime());
              userActivityJson.put("duration", CommonUtils.getTimeDiffernce(userActivity.getLoginTime(), userActivity.getLogoutTime()));
              userActivityJson.put("useStatus", "Active");
              userActityJsonArray.add(userActivityJson);
              
              //Next
              userActivityJson=new JSONObject();
              userActivityJson.put("userName", deviceIssueInfo.getUserInfo().getUserName());
              userActivityJson.put("inTime", reportResultInfo.getReportInfo().getLogoutTime());
              userActivityJson.put("outTime", reportResultInfo.getReportInfoNext().getLoginTime());
              userActivityJson.put("duration", CommonUtils.getTimeDiffernce(reportResultInfo.getReportInfo().getLogoutTime(), reportResultInfo.getReportInfoNext().getLoginTime()));
              userActivityJson.put("useStatus", "Idle");
              userActityJsonArray.add(userActivityJson);
            }
          }
          issueIdJson.put("userActivities", userActityJsonArray);
        }
        
        issueIdJsonArray.add(issueIdJson);
      }
      deviceJson.put("deviceId", deviceId);
      deviceJson.put("deviceIssueDetails", issueIdJsonArray);
      System.out.println("jsonObject: " + deviceJson);
    }
    return deviceJson;
    //return deviceIssueInfos;
  }
 

}

