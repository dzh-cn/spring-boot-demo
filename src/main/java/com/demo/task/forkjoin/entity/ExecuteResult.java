package com.demo.task.forkjoin.entity;

/**
 * 运行结果统计
 *
 * @author: dongzhihua
 * @time: 2018/5/22 11:47:08
 */
public class ExecuteResult {

	int taskCount; // 任务总数
	long onceMillis; // 一个任务处理时长
	long totalMillis; // 总处理时长
	long fjTotalMillis; // 使用Fork/Join技术的总处理时长
	int fjThreadCount; // Fork/Join启动线程个数
	float scale; // 总处理时长/使用技术的总处理时长

	public int getTaskCount() {
		return taskCount;
	}

	public void setTaskCount(int taskCount) {
		this.taskCount = taskCount;
	}

	public long getOnceMillis() {
		return onceMillis;
	}

	public void setOnceMillis(long onceMillis) {
		this.onceMillis = onceMillis;
	}

	public long getTotalMillis() {
		return totalMillis;
	}

	public void setTotalMillis(long totalMillis) {
		this.totalMillis = totalMillis;
	}

	public long getFjTotalMillis() {
		return fjTotalMillis;
	}

	public void setFjTotalMillis(long fjTotalMillis) {
		this.fjTotalMillis = fjTotalMillis;
	}

	public float getScale() {
		return scale;
	}

	public void setScale(float scale) {
		this.scale = scale;
	}

	public int getFjThreadCount() {
		return fjThreadCount;
	}

	public void setFjThreadCount(int fjThreadCount) {
		this.fjThreadCount = fjThreadCount;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("{");
		sb.append("\"taskCount\":")
				.append(taskCount);
		sb.append(",\"onceMillis\":")
				.append(onceMillis);
		sb.append(",\"totalMillis\":")
				.append(totalMillis);
		sb.append(",\"fjTotalMillis\":")
				.append(fjTotalMillis);
		sb.append(",\"fjThreadCount\":")
				.append(fjThreadCount);
		sb.append(",\"scale\":")
				.append(scale);
		sb.append('}');
		return sb.toString();
	}
}
