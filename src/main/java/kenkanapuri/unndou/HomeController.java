package kenkanapuri.unndou;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Controller
public class HomeController {

    record TaskItem(String id,String task,String deadline,boolean done){}
    private List<TaskItem> taskItems = new ArrayList<>();


    @RequestMapping(value="/健康管理アプリ")
    @ResponseBody
    String hello(){
        return """
            <html>
                <head><title>健康管理アプリ</title></head>
                <body>
                    <h1>健康管理アプリ</h1>
                    It works!<br>
                    現在の時刻は%ｓです。
                </body>
            </html>
            """.formatted(LocalDateTime.now());
            }
            @GetMapping("/restadd")
    String addItemd(@RequestParam("task")String task,
                     @RequestParam("deadline") String deadline){
        String id = UUID.randomUUID().toString().substring(0,8);
        TaskItem item = new TaskItem(id,task,deadline,false);
        taskItems.add(item);
        return "タスクを追加しました。";
    }
    @GetMapping("/restlist")
    String listItems(){
        String result = taskItems.stream()
                .map(TaskItem::toString)
                .collect(Collectors.joining(","));
        return result;
    }

    public static void main(String[] args) {

    }
}
