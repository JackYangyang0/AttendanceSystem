package com.cjj.attendance.controller;

import com.cjj.attendance.entity.Attendance;
import com.cjj.attendance.entity.DateNum;
import com.cjj.attendance.entity.ResultKit;
import com.cjj.attendance.service.AttendanceService;
import com.cjj.attendance.service.TeacherService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/attendance")
public class AttendanceController {

    @Autowired
    private AttendanceService attendanceService;

    @Autowired
    private TeacherService teacherService;

    private Map<String , Object> map = new HashMap<>();

    @GetMapping("/list")
    public ResultKit<List<Attendance>> attendanceList(@RequestParam(defaultValue = "1") int page ,
                                                      @RequestParam(defaultValue = "5") int limit,
                                                      String name ,
                                                      HttpServletRequest request){
        ResultKit<List<Attendance>> resultKit = new ResultKit<>();
        PageInfo<Attendance> pageInfo = null;
        if(name != null){
            pageInfo = attendanceService.searchAttendance(page , limit , name);
        }else{
            pageInfo = attendanceService.queryAttendance(page, limit);
        }
        resultKit.setCode(0);
        resultKit.setMsg("");
        resultKit.setCount((int)pageInfo.getTotal());
        resultKit.setData(pageInfo.getList());
        return resultKit;
    }

    @DeleteMapping("/delete")
    public ResultKit<Integer> deleteAttendance(@RequestParam String ids , HttpServletRequest request){
        ResultKit<Integer> resultKit = new ResultKit<>();
        String[] split = ids.split(",");
        System.out.println(split);
        int result = attendanceService.deleteAttendance(split);
        resultKit.setCode(0);
        resultKit.setMsg("");
        resultKit.setCount(result);
        resultKit.setData(result);
        return resultKit;
    }

    @PostMapping("/insert")
    @ResponseBody
    public ResultKit<Integer> insertAttendance(@RequestBody Attendance attendance ,
                                               HttpServletRequest request){
        System.out.println(attendance);
        ResultKit<Integer> resultKit = new ResultKit<>();
        int result = attendanceService.addAttendance(attendance);
        if(result != 0){
            resultKit.setCode(0);
            resultKit.setMsg("????????????");
        }else{
            resultKit.setCode(-1);
            resultKit.setMsg("????????????");
        }
        resultKit.setCount(result);
        resultKit.setData(result);
        return resultKit;
    }

    @PutMapping("/update")
    @ResponseBody
    public ResultKit<Integer> updateAttendance(@RequestBody Attendance attendance,
                                               HttpServletRequest request){
        System.out.println(attendance);
        ResultKit<Integer> resultKit = new ResultKit<>();
        int result = attendanceService.updateAttendance(attendance);
        if(result != 0){
            resultKit.setCode(0);
            resultKit.setMsg("????????????");
        }else{
            resultKit.setCode(-1);
            resultKit.setMsg("????????????");
        }
        resultKit.setCount(result);
        resultKit.setData(result);
        return resultKit;
    }

    @GetMapping("/getAttendanceDate")
    public Map<String , Object> getAttendanceDate(HttpSession session){
        List<DateNum> dateNum = attendanceService.getDateNum();
        int[] data = new int[12];
        for (DateNum num : dateNum) {
            data[num.getMonth() - 1] = num.getCount();
        }
        map.put("data" , data);
        return map;
    }

    @GetMapping("/getClazAttendanceDate")
    public Map<String , Object> getClazAttendanceDate(HttpSession session){
        String username = (String)session.getAttribute("username");
        String claz = teacherService.queryClaz(username);
        List<DateNum> clazDateNum = attendanceService.getClazDateNum(claz);
        int[] data = new int[12];
        for (DateNum num : clazDateNum) {
            data[num.getMonth() - 1] = num.getCount();
        }
        map.put("claz" , claz);
        map.put("data" , data);
        return map;
    }

    @GetMapping("/getMyAttendanceDate")
    public Map<String , Object> getMyAttendanceDate(HttpSession session){
        String stuId = (String)session.getAttribute("username");
        List<DateNum> clazDateNum = attendanceService.getMyDateNum(stuId);
        int[] data = new int[12];
        for (DateNum num : clazDateNum) {
            data[num.getMonth() - 1] = num.getCount();
        }
        map.put("data" , data);
        return map;
    }
}
