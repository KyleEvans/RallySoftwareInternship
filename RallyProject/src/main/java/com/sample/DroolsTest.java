package com.sample;

import org.drools.KnowledgeBase;
import org.drools.KnowledgeBaseFactory;
import org.drools.builder.KnowledgeBuilder;
import org.drools.builder.KnowledgeBuilderError;
import org.drools.builder.KnowledgeBuilderErrors;
import org.drools.builder.KnowledgeBuilderFactory;
import org.drools.builder.ResourceType;
import org.drools.io.ResourceFactory;
import org.drools.logger.KnowledgeRuntimeLogger;
import org.drools.logger.KnowledgeRuntimeLoggerFactory;
import org.drools.runtime.StatefulKnowledgeSession;

/**
 * This is a sample class to launch a rule.
 */
public class DroolsTest {

	public static final void main(String[] args) {

		try {
			// load up the knowledge base
			KnowledgeBase kbase = readKnowledgeBase("Sample.drl");
			StatefulKnowledgeSession ksession = kbase
					.newStatefulKnowledgeSession();
			KnowledgeRuntimeLogger logger = KnowledgeRuntimeLoggerFactory
					.newFileLogger(ksession, "test");
			// go !

			Computer c1 = new Computer();
			Computer c2 = new Computer();
			Computer c3 = new Computer();
			Computer c4 = new Computer();
			Computer c5 = new Computer();
			Computer c6 = new Computer();

			c1.setName("Rosie");
			c1.setMem(2);
			c1.setCpu(5);
			c1.setCard("none");
			c1.setCost(800);
			c2.setName("Hal"); 
			c2.setMem(8);
			c2.setCpu(3);
			c2.setCard("average");
			c2.setCost(1500);
			c3.setName("Kitt");
			c3.setMem(1);
			c3.setCpu(1);
			c3.setCard("none1");
			c3.setCost(400);
			c4.setName("Jarvis");
			c4.setMem(16); 
			c4.setCpu(4);
			c4.setCard("premium");
			c4.setCost(3000);
			c5.setName("Tom Servo"); 
			c5.setMem(32); 
			c5.setCpu(5);
			c5.setCard("none");
			c5.setCost(8000); 
		    c6.setName("T-1000");
		    c6.setMem(2); 
			c6.setCpu(2);
			c6.setCard("premium");
			c6.setCost(5500);
			 

			ksession.insert(c1);
			ksession.fireAllRules();
			ksession.insert(c2);
			ksession.fireAllRules();
			ksession.insert(c3);
			ksession.fireAllRules();
			ksession.insert(c4);
			ksession.fireAllRules();
			ksession.insert(c5); 
			ksession.fireAllRules();
			ksession.insert(c6); 
			ksession.fireAllRules();
			 

			logger.close();
			
			KnowledgeBase kbase1 = readKnowledgeBase("Sample1.drl");
			StatefulKnowledgeSession ksession1 = kbase1
					.newStatefulKnowledgeSession();
			KnowledgeRuntimeLogger logger1 = KnowledgeRuntimeLoggerFactory
					.newFileLogger(ksession1, "test");
			
			
			
			ksession1.insert(c1);
			ksession1.insert(c2);
			//ksession1.fireAllRules();
			
			ksession1.insert(c3);
			//ksession1.fireAllRules();
			ksession1.insert(c4);
			ksession1.fireAllRules();
			ksession1.insert(c5); 
			ksession1.fireAllRules();
			ksession1.insert(c6); 
			ksession1.fireAllRules();
			logger1.close();
			
			
		} catch (Throwable t) {
			t.printStackTrace();
		}
	}

	private static KnowledgeBase readKnowledgeBase(String filename) throws Exception {
		KnowledgeBuilder kbuilder = KnowledgeBuilderFactory
				.newKnowledgeBuilder();
		kbuilder.add(ResourceFactory.newClassPathResource(filename),
				ResourceType.DRL);
		KnowledgeBuilderErrors errors = kbuilder.getErrors();
		if (errors.size() > 0) {
			for (KnowledgeBuilderError error : errors) {
				System.err.println(error);
			}
			throw new IllegalArgumentException("Could not parse knowledge.");
		}
		KnowledgeBase kbase = KnowledgeBaseFactory.newKnowledgeBase();
		kbase.addKnowledgePackages(kbuilder.getKnowledgePackages());
		return kbase;
	}

	public static class Computer {
		// specs
		public String gfxCard = "none";
		public String modelName = "";
		public int ram = 0;
		public int cpu = 0;
		public int price = 0;
		// traits
		public boolean isLoud = false;
		public boolean isRisky = false;
		public String relativeCost = "";
		public boolean canGame = false;
		public boolean isHot = false;
		public boolean canCrunch = false;

		public String getCard() {
			return this.gfxCard;
		}

		public void setCard(String card) {
			this.gfxCard = card;
		}

		public String getName() {
			return this.modelName;
		}

		public void setName(String name) {
			this.modelName = name;
		}

		public int getMem() {
			return this.ram;
		}

		public void setMem(int mem) {
			this.ram = mem;
		}

		public int getCpu() {
			return this.cpu;
		}

		public void setCpu(int cpu) {
			this.cpu = cpu;
		}

		public int getCost() {
			return this.price;
		}

		public void setCost(int price) {
			this.price = price;
		}

		public void setLoud() {
			this.isLoud = true;
		}

		public void setRisky() {
			this.isRisky = true;
		}

		public void setGame() {
			this.canGame = true;
		}

		public void setHot() {
			this.isHot = true;
		}

		public void setCrunch() {
			this.canCrunch = true;
		}

		public void setRelativeCost(String cost) {
			relativeCost = cost;
		}
	}

}