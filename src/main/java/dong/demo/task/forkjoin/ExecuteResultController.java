package dong.demo.task.forkjoin;

import dong.demo.task.forkjoin.entity.ExecuteResult;
import dong.demo.task.forkjoin.mapper.ExecuteResultMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;

/**
 * controller
 *
 * @author: dongzhihua
 * @time: 2018/5/24 16:55:28
 */
@Controller
@RequestMapping("execute/result")
public class ExecuteResultController {

	@Autowired
	ExecuteResultMapper executeResultMapper;

	@GetMapping("qByOnceMilli")
	@ResponseBody
	public List<ExecuteResult> queryByOnceMilli(Integer onceMilli) {
		return executeResultMapper.selectByOnceMilli(onceMilli);
	}

	@RequestMapping("allGroup")
	@ResponseBody
	public HashMap<Object, List<ExecuteResult>> allGroup(Integer[] onceMillis) {
		HashMap<Object, List<ExecuteResult>> result = new HashMap<>();
		for (Integer onceMilli : onceMillis) {
			result.put(onceMilli, executeResultMapper.selectByOnceMilli(onceMilli));
		}
		return result;
	}

	@GetMapping("statistics")
	public String statistics(Integer[] onceMilli, Model model) {
		model.addAttribute("om", onceMilli);
		return "forkjoin/statistics";
	}
}
