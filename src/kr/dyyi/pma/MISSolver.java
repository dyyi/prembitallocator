package kr.dyyi.pma;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;

public class MISSolver {
	public static void run(HashMap<HashSet<LogicComponent>, Double> densityHash, String output, Double limit) {
		MISSolver.run(densityHash, new File(output), limit);
	}
	public static void run(HashMap<HashSet<LogicComponent>, Double> densityHash, File output, Double limit) {
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(output));
			//System.out.println("## Initialize nodes!! ");
			HashMap<HashSet<LogicComponent>, Double> ffHash = densityHash;
			Iterator<HashSet<LogicComponent>> itFSE = LogicAnalyzer.sortByValue(ffHash).iterator();
			ArrayList<LogicGraphVertex> arrList = new ArrayList<LogicGraphVertex>();
			while (itFSE.hasNext()) {
				HashSet<LogicComponent> fse = itFSE.next();
				if (ffHash.get(fse) < limit) {
					break;
				}
				LogicGraphVertex lgv = new LogicGraphVertex(fse);
				//System.out.println(ffHash.get(fse));
				if (!arrList.contains(lgv)) {
					arrList.add(lgv);
				}
			}
	
			//System.out.println("## Drawing conflict edge!! ");
			for (LogicGraphVertex lgv : arrList) {
				for (LogicGraphVertex secondLgv : arrList) {
					if (lgv == secondLgv) {
						continue;
					}
					if (lgv.isConflict(secondLgv)) {
						lgv.addAdjacentVertex(secondLgv);
						secondLgv.addAdjacentVertex(lgv);
					}
				}
			}
			//System.out.println("## Find independent set!! ");
			LinkedList<LogicComponent> resultCheckList = new LinkedList<LogicComponent>();
			while (arrList.size() > 0) {
				LogicGraphVertex maxLgv = null;
				Double maxWeight = 0.0;
				for (LogicGraphVertex lgv : arrList) {
					if (maxLgv == null || maxWeight > lgv.getWeight()) {
						maxLgv = lgv;
						maxWeight = lgv.getWeight();
					}
				}
				// Print Set
				if (maxLgv.getSet().size() > 1) {
					bw.write("create_multibit {");
					Iterator<LogicComponent> maxLgvSetIt = maxLgv.getSet().iterator();
					while (maxLgvSetIt.hasNext()) {
						LogicComponent lc = maxLgvSetIt.next();
						bw.write(" " + lc.getName());
						if (resultCheckList.contains(lc)) {
							System.err.println("Conflict! " + lc.getName());
							System.exit(0);
						}
						resultCheckList.add(lc);
					}
					bw.write(" }");
					bw.newLine();
				}
				// Remove Node
				while (maxLgv.getAdjacentVertexList().size() > 0) {
					LogicGraphVertex adjacentLgv = maxLgv.getAdjacentVertexList().get(0);
					for (LogicGraphVertex farAdjLgv : adjacentLgv.getAdjacentVertexList()) {
						farAdjLgv.removeAdjacentVertex(adjacentLgv);
					}
					arrList.remove(adjacentLgv);
					maxLgv.getAdjacentVertexList().remove(adjacentLgv);
				}
				arrList.remove(maxLgv);
			}
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
		
}
