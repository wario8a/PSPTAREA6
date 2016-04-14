package Statistics;

import java.util.LinkedList;
/**
*Nombre del Programa: Libreria de Calculo de variables Estadisticas
*@author Mario Andres Ochoa Camacho
*@version 1.6
*Fecha: 13/04/2016
*Descripcion: Calcula varias Variables Estadisticas dado un array de double

*Import:ava.util.LinkedList
*Paquete: 
*Clase: Statistics
*Metodos: Mean, StandardDeviation,Summatory,PotSummatory,ProdSummatory,Average,LinearRegrationCalcb0,LinearRegrationCalcb1
*LinearRegrationCalcCorrelation,InSummatory,InVariance,StandardDeviation,GetRanges, CalcSimpson, CalctDistribution, CalcGamma
*/
public class Statistics {
/**
 * Calcula la Mediana de un grupo de numeros en una LinkedList, los datos dentro del LinkedList deben ser convertibles a decimales
 * @param linkedList LinkedList con los datos a los cuales se les hara el calculo 
 * @return Mediana de los valores Ingresados
 */
	public static double Mean(LinkedList<String> linkedList){
		
		double Mean = 0.0;
		
		if(!linkedList.isEmpty()){
			for (Object item : linkedList)
			      Mean += new Double(item.toString());
			
			Mean = Mean/linkedList.size();
		}
		
		return Mean;
	}
	
/**
 * Calcula la Desviacion Estandar de un grupo de numeros en una LinkedList, los datos dentro del LinkedList deben ser convertibles a decimales
 * @param linkedList LinkedList con los datos a los cuales se les hara el calculo 
 * @return Desviacion Estandar de los valores Ingresados
 */
	public static double StandardDeviation(LinkedList<String> linkedList){
		double dbStanDev =0.0;
		
		if(!linkedList.isEmpty()){
			
			double Mean = Mean(linkedList);
			
			for (Object item : linkedList)
				dbStanDev += Math.pow(new Double(item.toString()) - Mean,2);
			
			dbStanDev = Math.pow(dbStanDev/(linkedList.size() - 1),0.5);
		}
		
		return dbStanDev;
	}
/**
 * Sumatoria de un grupo de valores ingresados en un arreglo de decimales	
 * @param values Arreglo de decimales con los valores a los cuales se aplicara la sumatoria
 * @return Sumatoria de los valores Ingresados
 */
	private static double Summatory(double [] values){
		double summatory=0;
		
		for(int count=0; count < values.length; count++){
			summatory += values[count];
		}
		
		return summatory;
	}
/**
 * Sumatoria de las potencias de un grupo de valores ingresados en un arreglo de decimales	
 * @param values Arreglo de decimales de los valores de los cuales se quiere la sumatoria
 * @param pot Potencia que se le aplicara a cada valor antes de la Suma
 * @return Sumatoria de Potencia de los valores ingresados
 */
	private static double PotSummatory(double [] values, int pot){
		double potSummatory=0;
		for(int count=0; count < values.length; count++){
			potSummatory += Math.pow(values[count],pot);
		}				
		return potSummatory;
	}
/**
 * Sumatoria de los productos entre los valores de dos arreglos de decimales, los arreglos deben contener
 * la misma cantidad de numeros, de lo contrario devuelve 0 en la respuesta 	
 * @param xvalues Arreglo numero uno del calculo
 * @param yvalues Arreglo numero dos del calculo
 * @return Sumatorio de los productos de los dos arreglos
 */
	private static double ProdSummatory(double [] xvalues, double [] yvalues){
		double prodsummatory=0;
		
		if(xvalues.length == yvalues.length){
			for(int count=0; count < xvalues.length; count++){
				prodsummatory += xvalues[count] * yvalues[count];
			}		
		}else{
			return 0;
		}		
		return prodsummatory;
	}
	
/**
 * Promedio de valores de un arreglo de decimales	
 * @param values Arreglo de Decimales a los cuales se les efectuara el calculo
 * @return Promedio de los Valores
 */
	private static double Average(double [] values){
		return Summatory(values)/values.length;
	}

/**
 * Calculo de el B0 de una Regresion Lineal dados los arreglos de decimales de x y y	
 * @param xvalues Arreglo de Decimales de los valores de x
 * @param yvalues Arreglo de Decimales de los valores de y
 * @return Retorna el valor B0 de la regresion Lineal
 */
	public static double LinearRegrationCalcb0(double [] xvalues, double [] yvalues){
		return Average(yvalues) - LinearRegrationCalcb1(xvalues, yvalues) * Average(xvalues);
	}

/**
 * Calculo de el B1 de una Regresion Lineal dados los arreglos de decimales de x y y	
 * @param xvalues Arreglo de Decimales de los valores de x
 * @param yvalues Arreglo de Decimales de los valores de y
 * @return Retorna el valor B1 de la regresion Lineal
 */
	public static double LinearRegrationCalcb1(double [] xvalues, double[] yvalues){
		double b1=0;
		double divisor =1;
		if(xvalues.length == yvalues.length){
			b1 = ProdSummatory(xvalues,yvalues) - xvalues.length*Average(xvalues)*Average(yvalues);
			divisor = PotSummatory(xvalues,2) - xvalues.length*Math.pow(Average(xvalues),2);						
		}else{
			return 0;
		}
		return b1/ divisor;
	}

/**
 * Calculo de la Correlacion de la Regresion Lineal r
 * @param xvalues Arreglo de Decimales de los valores de x
 * @param yvalues Arreglo de Decimales de los valores de y
 * @return Retorna el valor de la correlacion de la regresion Lineal
 */
	public static double LinearRegrationCalcCorrelation(double [] xvalues, double[] yvalues){
		double correlation=0;
		double divisor =1;
		if(xvalues.length == yvalues.length){
			correlation = xvalues.length * ProdSummatory(xvalues, yvalues) - Summatory(xvalues) * Summatory(yvalues);
			divisor = Math.pow((xvalues.length * PotSummatory(xvalues,2) - Math.pow(Summatory(xvalues), 2))*((yvalues.length * PotSummatory(yvalues,2) - Math.pow(Summatory(yvalues), 2))), 0.5);
		}else{
			return 0;
		}	
		
		return correlation/ divisor;
	}

	/**
	 * Calculo de la Sumatoria de los Logaritmos Naturales de un arreglo de decimales
	 * @param values Arreglo de Numeros decimales
	 * @return Retorna el valor de la sumatoria de los logaritmos naturales
	 */
	public static double InSummatory(double [] values){
		double summatory=0;
		
		for(int count=0; count < values.length; count++){
			summatory += Math.log(values[count]);
		}
		
		return summatory;
	}

	/**
	 * Calcula la Variancia de los Logaritmos naturales de un arreglo de decimales
	 * @param values Arreglo de Numeros decimales
	 * @return Retorna el valor de de la variancia de los logaritmos naturales
	 */
	public static double InVariance(double [] values){
		double inVariance=0;
		double average = InSummatory(values) / values.length;
		
		for(int count=0; count < values.length; count++){
			inVariance += Math.pow(Math.log(values[count]) -average,2);
		}
		
		return inVariance / (values.length -1);
	}
	
	/**
	 * Calcula los valores Realtivos de una tabla, Donde la posicion
	 * 0 es VS, 1 S, 2 M, 3 L, 4 VL. Estos valore son retornados en un Linked List
	 * @param values Arreglo con los Numeros Decimales
	 * @return Retorno un Linked List con los valores del calculo
	 */
	public static LinkedList<Double> GetRanges (double [] values){
		LinkedList<Double> linkedList = new LinkedList<Double>();
		
		double average = InSummatory(values) / values.length;
		double standardDeviation = Math.pow(InVariance(values),0.5);
		
		linkedList.add(Math.exp(average - 2 *standardDeviation ));
		linkedList.add(Math.exp(average - standardDeviation ));
		linkedList.add(Math.exp(average ));
		linkedList.add(Math.exp(average  + standardDeviation ));
		linkedList.add(Math.exp(average + 2 *standardDeviation ));
		
		return linkedList;
	}
	
	/**
	 * Calcula la Funcion de Simpson
	 * @param x0 Valor inicial de x
	 * @param x1 valor final de x
	 * @param numSeg Numero de Segmentos
	 * @param dof grados de libertad
	 * @return Resultado
	 */
	public static double CalcSimpson(double x0, double x1, int numSeg, int dof){
		double result = 0;
		
		for(int i =0; i<= numSeg;i++){			
			if(i==0){
				result += CalctDistribution(x0,(double)dof);
			}else if(i == numSeg){
				result += CalctDistribution(x1,(double)dof);
			}else if(i%2 == 0){
				result += 2 * CalctDistribution(x1/numSeg * i ,(double)dof);
			}else{
				result += 4 * CalctDistribution(x1/numSeg * i ,(double)dof);
			}			
		}
		
		return (result * x1) / (numSeg * 3);
	}
	
	/**
	 * Calcula la funcion t distribution
	 * @param x Valor de X
	 * @param dof Grados de libertad
	 * @return Resultado
	 */
	public static double CalctDistribution(double x, double dof){
		double result=0;		
		result = CalcGamma((dof + 1)/2) * Math.pow((1 + (Math.pow(x, 2)/dof)),-1*(dof+1)/2);		
		return result / (Math.sqrt(dof*Math.PI) * CalcGamma(dof/2));
	}
	
	/**
	 * Cualcula la Funcion Gamma de un numero dado
	 * @param value Valor al cual se le calculara la Funcion Gamma
	 * @return Resultado
	 */
	public static double CalcGamma(double value){
		double result = 1;
		
		do{
			value = value-1;
			if(value == 0.5){
				result *= value * Math.sqrt(Math.PI);
				break;
			}else{
				result *= value == 0?1:value;
			}
		}while (value >= 0.5);
		
		return result;
	}
}
