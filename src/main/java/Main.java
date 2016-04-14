import java.io.IOException;

import Statistics.Statistics;
/**
*Nombre del Programa: Integration Of the t Function Using Simpson Rule
*@author Mario Andres Ochoa Camacho
*@version 1.0
*Fecha: 13/04/2016
*Descripcion: Calcula la Integral de la funcion t usando la Regla de Simpson

*Import: java.io.IOException
*Paquete: Statistics
*Clase: Main
*Metodos: main,BeginCalc

*Instrucciones de Uso:
*Solo es ecesario ejecutar el programa y se calculan los vlaores, si se desean calcualr otros es necesario modificar el programa
*/
public class Main {
/**
 * Clase Princial
 * @param args Argumentos de Inicio del Main
 */
  public static void main(String[] args ){
			
	  System.out.println("Para p=0.2   y con dof = 6,  el valor de x = " + IteratorX(0.20, 6));
	  System.out.println("Para p=0.45  y con dof = 15, el valor de x = " + IteratorX(0.45, 15));
	  System.out.println("Para p=0.495 y con dof = 4,  el valor de x = " + IteratorX(0.495, 4));
  }
  
  /**
   * Itera el valor x hasta encontrar el correcto para el p dado
   * @param pvalue El valor de p dado
   * @param dof Grados de libertad
   * @return Valor de x encontrado
   */
  private static double IteratorX(double pvalue, int dof){
	  double result=0;
	  double xvalue = 1.0;
	  double error = 0.00001;
	  double actualError = 0;
	  double dvalue=0.5;
	  
	  result = Statistics.CalcSimpson(0.0, xvalue, 10, dof);
	  if(Math.abs(result - pvalue)< 0.00001){
		  return xvalue;
	  }
	  if(result < pvalue){
		  xvalue += dvalue;
	  }else{
		  xvalue -= dvalue;
	  }
	  actualError = result - pvalue;
	  do{
		  result = Statistics.CalcSimpson(0.0, xvalue, 10, dof);
		  /*
		   * Verifica el valor actual del error si cumple con lo solicitado
		   */
		  if(Math.abs(result - pvalue)< error){
			  return xvalue;
		  }
		  /*
		   * Calcula el ajuste de d
		   */
		  dvalue = CalcAdjustd(dvalue,actualError,pvalue,result);
		  if(result < pvalue){			  
			  xvalue += dvalue;
		  }else{
			  xvalue -= dvalue;
		  } 
		  actualError = result - pvalue;
	  }while(Math.abs(actualError) > error);
	  
	  return xvalue;
  }
  
  /**
   * Adjusta el Valor de d de acuerdo al error pasado y el actual
   * @param dvalue Actual valor de d
   * @param actualError Error actual
   * @param pvalue Valor actual de p
   * @param pactual Valor calculado de p
   * @return Valor ajustado de d
   */
  private static double CalcAdjustd(double dvalue, double actualError, double pvalue, double pactual){  
	  if(actualError * (pactual -pvalue ) >=0){
		  return dvalue;
	  }else{
		  return dvalue/2;
	  }
  }
}