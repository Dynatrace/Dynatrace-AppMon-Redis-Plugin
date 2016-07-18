package com.dynatrace.plugin.redis;

public class Memory {

	private double used_memory;
	private double used_memory_peak;
	
	public double getUsed_memory() {
		return used_memory;
	}
	public void setUsed_memory(double used_memory) {
		this.used_memory = used_memory;
	}
	public double getUsed_memory_peak() {
		return used_memory_peak;
	}
	public void setUsed_memory_peak(double used_memory_peak) {
		this.used_memory_peak = used_memory_peak;
	}
	
}
