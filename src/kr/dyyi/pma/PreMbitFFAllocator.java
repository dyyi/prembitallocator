package kr.dyyi.pma;

import java.io.File;
import java.util.HashMap;
import java.util.HashSet;

public class PreMbitFFAllocator {
	public static final Integer DENSITY_STEP = 5;
	public static final Integer INITIAL_TARGET_DENSITY = 1;
	
	public PreMbitFFAllocator() { ; }
	
	public void run(Verilog verilog, LEF lef) {
		System.out.println("* Traversing logic structures ...");
		HashMap<HashSet<LogicComponent>, Double> densityHash = LogicAnalyzer.run(verilog, lef);
		
		File runScript = new File("./00_RUN/scripts/dc_run.tcl");
		boolean timingCheck = true;
		Integer currentDensity = INITIAL_TARGET_DENSITY;
		while (timingCheck || currentDensity < 100) {
			System.out.println("\t> MBITFF Allocating for target density: " + currentDensity);
			new File(./0)
			MISSolver.run(densityHash, new File("./02_MBITFF/scripts/create_multibit.tcl"), (currentDensity + 0.0)/1000);

			currentDensity += DENSITY_STEP;
		}
		//.run(densityHash, new File("2.tcl"), 0.02);
		//MISSolver.run(densityHash, new File("3.tcl"), 0.03);
		//MISSolver.run(densityHash, new File("50.tcl"), 0.50);
	}
	
	public static void main(String[] args) {
		PreMbitFFAllocator pma = new PreMbitFFAllocator();
		pma.run(new Verilog(args[0]), new LEF(args[1]));
	}
}
