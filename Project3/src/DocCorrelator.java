import java.util.*;

class DocCorrelator {
	
	
	
	public static double DocCorrelate(String file1, String file2, String flag){
		DataCount<String>[]list1 = WordCount.countWords(file1, flag);
		DataCount<String>[]list2 = WordCount.countWords(file2, flag);
		WordFreq[] list1Nor = docNormalize(list1);
		WordFreq[] list2Nor = docNormalize(list2);
		double correlator = 0;
		int i, ii;
		
		for (i = 0; i < list1Nor.length; i++){
			for(ii = 0; ii < list2Nor.length; ii++){
				if(list1Nor[i].compareTo(list2Nor[ii]) == 0){
					correlator = correlator + Math.pow(list1Nor[i].frequency - list2Nor[ii].frequency, 2);
				}
			}
		}
		
		printComparison(list1Nor, list2Nor);
		System.out.println("The correlator number: " + correlator + "(%^2)");
		return correlator;
	}
	
	static public WordFreq[] docNormalize(DataCount<String>[] list){
		ArrayList<WordFreq> listNor = new ArrayList<WordFreq>();
		final double min_freq = 0.01;
		final double max_freq = 1;
		double freq = 0;
		int i;
		int wordSum = 0;
		WordFreq newData;

		for (i = 0; i < list.length; i++){
		wordSum = wordSum + list[i].count;
		}
		
		for (i = 0; i < list.length; i++){
			freq = ((double)list[i].count / wordSum) * 100;
			if(freq >= min_freq && freq <= max_freq){
				newData = new WordFreq(list[i].data, freq);
				listNor.add(newData);
			}
		}
		
		return listNor.toArray(new WordFreq[listNor.size()]);
	}
	
	public static void printComparison(WordFreq[] list1, WordFreq[] list2){
		int max_length = Math.max(list1.length, list2.length);
		
		for(int i = 0; i < max_length; i++){
			if(i < list1.length){
				System.out.printf("%20s\t%.5f%s\t\t", list1[i].word, list1[i].frequency, "%");
			}
			if(i < list2.length){
				System.out.printf("%20s\t%.5f%s\t\t", list2[i].word, list2[i].frequency, "%");
			}
			System.out.println();
		}
	}
	
	public static void main(String[] args) {
		DocCorrelate(args[0], args[1], args[2]);
	}
	

}
