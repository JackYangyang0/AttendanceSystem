package com.cjj.attendance.controller;

import com.cjj.attendance.entity.Attendance;
import com.cjj.attendance.entity.ResultKit;
import com.cjj.attendance.service.AttendanceService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/attendance")
public class AttendanceController {

    @Autowired
    private AttendanceService attendanceService;

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
            resultKit.setMsg("添加成功");
        }else{
            resultKit.setCode(-1);
            resultKit.setMsg("添加失败");
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
            resultKit.setMsg("修改成功");
        }else{
            resultKit.setCode(-1);
            resultKit.setMsg("修改失败");
        }
        resultKit.setCount(result);
        resultKit.setData(result);
        return resultKit;
    }

}
