import numpy as np
import matplotlib.pyplot as plt
import sympy as sp
import sys
x=sp.symbols('x')

def pause():
  pause = input("Presione <ENTER> para continuar")

def prettier(func):
  func.lower()
  func.strip()
  func = func.replace("log", "np.log")
  func = func.replace("sqrt", "np.sqrt")
  func = func.replace("cos", "np.cos")
  func = func.replace("sin", "np.sin")
  func = func.replace("tan", "np.tan")
  func = func.replace("exp", "np.exp")
  return func

def funtion(x, func):
  return eval(func)

######metodos de raices########
def grafico(func):
#  global x
#  fx = sp.sympify(func)
#  sp.plot(sp.log(x))
  func = prettier(func)
  x = np.linspace(-10, 10, num=100)
  plt.plot(x,funtion(x, func), 'r')
  plt.grid();
  plt.xlabel('x')
  plt.ylabel('y')
  plt.axvline();
  plt.axhline();
  plt.title('Parcial 1')
  plt.show();

def CalcError(xrAnterior, xrNuevo):
  return abs((xrNuevo - xrAnterior) / xrNuevo) * 100

def biseccion(func):
  funv = prettier(func)
  xi = float(input("ingrese el limite inferior: "))
  xs = float(input("ingrese el limite superior: "))
  error = float(input("ingrese el error maximo: "))
  e = 100
  i = 100
  k = 0
  xr = 0
  while k<i:
    xa = (xs + xi)/2
    x = xa
    fxa = eval(funv)
    x = xi
    fxi = eval(funv)
    if(fxa*fxi > 0):
      xi = xa
    else:
      if(fxa*fxi < 0):
        xs = xa
      else:
        break
    if(xr != 0 and (CalcError(xr,xa) < error)):
      break
    xr = xa
    k+=1
  if(k>=i):
    print("La funcion no converge")
  else:
    print("Raiz aproximada en: ", xa)
    plt.plot([xa, xa], [10, -10], 'b--')
    grafico(func)

def punto_fijo(func):
  fPF = sp.sympify(func)+x
  xr = float(input("Digite el x0:"))
  e = float(input("Digite el error:"))
  i=0
  xn = xr
  maxIter=100
  while i<maxIter:
    xn = fPF.evalf(subs={x:xr})
    xr = xn
    i+=1
    if(xr!=0 and CalcError(xr, xn)<e):
      break
  if(i>=maxIter):
    print("El metodo no converge")
  else:
    print("Raiz aproximada en: ",xr)
    plt.plot([xr, xr], [10, -10], 'b--')
    grafico(func)

def newthon_raphson(func):
  funcv = sp.sympify(func)
  dfunc = sp.diff(funcv)
  xr = float(input("Digite el x0:"))
  e = float(input("Digite el error:"))
  fNR = x-(funcv/dfunc)
  i=0
  xn = 1
  maxIter=100
  while i<maxIter:
    xn = fNR.evalf(subs={x:xr})
    xr=xn
    i+=1
    if(xr!=0 and CalcError(xr,xn)<e):
      break
  if(i>=maxIter):
    print("El metodo no converge")
  else:
    print("Raiz aproximada en: ",xr)
    plt.plot([xr, xr], [10, -10], 'b--')
    grafico(func)

def metodo_de_la_secante(func):
  funv = prettier(func)
  xiant = float(input("Ingrese el primer valor: "))
  xi = float(input("Ingrese el segundo valor: "))
  e = float(input("Digite el error:"))
  i=100
  k = 0
  while k<i:
    x = xi
    fxi = eval(funv)
    x = xiant
    fxiant = eval(funv)
    aux = fxi-fxiant
    if (aux!=0):
      aux+=0.0000001
    xi_1=xi-((fxi*(xi-xiant))/aux)
    xiant=xi
    xi=xi_1
    if(CalcError(xi, xi_1)<e):
      break;
    k=k+1 
  if(k>=i):
    print("La funcion no converge")
  else:
    print("Raiz aproximada en: ",xi_1)
    plt.plot([xi_1, xi_1], [10, -10], 'b--')
    grafico(func)

####metodos de integracion####
def regla_del_trapecio(func):
  func = prettier(func)
  a = int(input("Ingrese el limite inferior: "))
  b = int(input("Ingrese el limite superior: "))
  n = int(input("Ingrese el numero de subintervalos que desea: "))
  dif= (b-a)/n
  area=0
  j=0
  while j<n:
    x = a
    fa = eval(func)
    x = dif+a
    fb = eval(func)
    h = max(fa, fb)
    a+=dif
    area+=dif*((fa+fb)/2)
    j=j+1
  print("El area aproximada es: ", area)
  pause()

def simpson_1_3(func):
  func = prettier(func)
  a = float(input("Digite el intervarlo inferior: "))
  b = float(input("Digite el intervarlo superior: "))
  n = int(input("Digite el numero de intervalos: "))
  h = (b-a)/n
  suma = 0.0
  for i in range(1, n):
    x = a+i*h
    if(i%2==0):
      suma = suma+2*funtion(x, func)
    else:
      suma = suma+4*funtion(x, func)
  suma = suma+funtion(a, func)+funtion(b, func)
  rest = suma*(h/3)
  print("El area aproximada es: ",rest)
  pause()

def simpson_3_8(func):
  func = prettier(func)
  a = float(input("Digite el intervarlo inferior: "))
  b = float(input("Digite el intervarlo superior: "))
  n = int(input("Digite el numero de intervalos: "))
  h = (b-a)/n
  suma = funtion(a, func)+funtion(b, func)
  for i in range(1,n):
    k = a+i*h
    if i%2==0:
      suma=suma+2*funtion(k, func)
    else:
      suma=suma+3*funtion(k, func)
  suma=suma*3*h/8
  print("El area aproximada es: ",suma)
  pause()

##MENU 
def menu():
  print("0) SALIR")
  print("1) DIGITAR FUNCION")
  print("METODOS DE RAICES")
  print("2) GRAFICO")
  print("3) BISECCION")
  print("4) JO")
  print("5) NEWTHON_RAPHSON")
  print("6) SECANTE")
  print("METODOS DE INTEGRACION")
  print("7) REGLA DEL TRAPECIO")
  print("8) SIMPSON 1/3")
  print("9) SIMPSON 3/8")

while True:
  menu();
  opciones = {
    1: '',
    2: grafico,
    3: biseccion,
    4: punto_fijo,
    5: newthon_raphson,
    6: metodo_de_la_secante,
    7: regla_del_trapecio,
    8: simpson_1_3,
    9: simpson_3_8,
    }
  inp = int(input("Escoja una opcion: "))
  opc = opciones.get(inp, lambda:print("Opcion no valida"))
  if(inp == 0):
    sys.exit()
  if(inp == 1):
    func = input("Digite la funcion:\n")
  else:
    try:
      opc(func)
    except:
      print('Hubo un error inesperado')
      pause()
