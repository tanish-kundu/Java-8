s = r'C:\n' # raw string, r disables most escape sequence processing
print(s)

# automatically \n is included in string breaks. To prevent, use \
print(""" hello\
friends
""")

print("s"+'b'+str(2))

## the ones enclosed between quotes next to each other are automatically concatenated
print('Py''thon')

# only works with two literals
print('Put several strings within parentheses '
      'to have them joined together.')

string = "python"

# Indexing
print(string[1])

print(string[-1])  # last character

print(string[-2])  # second-last character

print(string[-4])

# Slicing
print(string[0:2])  # characters from position 0 (included) to 2 (excluded)

# s[:i] + s[i:] is always equal to s

print(string[2:]+string[:2]) # thon + py

print(string[:3]+string[3:]) # python

print(string[-2:]) # on  characters from the second-last (included) to the end

"""
+---+---+---+---+---+---+
| P | y | t | h | o | n |
+---+---+---+---+---+---+
0   1   2   3   4   5 
-6  -5  -4  -3  -2  -1

"""

print(string[-6])

## Python strings cannot be changed — they are immutable

#string[0] = 'j'; # error

# Triple quoted strings may span multiple lines -
# all associated whitespace will be included in the string literal

print(string[0]==string[0:1])

## LISTS

squares = [1, 4, 9, 16, 25]
print(squares)

# Like strings (and all other built-in sequence types), lists can be indexed and sliced
squares[0]  # indexing returns the item

squares[-1]

squares[-3:]  # slicing returns a new list

squares[:] # return shallow copy

print(squares + [36, 49, 64, 81, 100]) # lists support concatenation

squares[1] = 32 # lists are mutable

print(squares)

squares.append(10)
print(squares)

#squares.clear() # OR squares[:] = []
print(len(squares))

# lists can be embedded
a = ['a', 'b', 'c']
n = [1, 2, 3]
x = [a, n]

print(x)

def fib():
    a,b = 0,1
    while a<10:
        print(a, end=',') # end used to remove newline character and put specified one
        a,b = b, a+b

print("the fibonacci series is ", fib())

def ifs(x):
    if x<5:
        print(5)
    elif x>5:
        print(11)
    else:
        print('nothing');
    return 'done';

print("values are ", ifs(2))

words = ['cat', 'window', 'defenestrate']
for w in words:
    print(w, len(w), 'haha')

del words[0]

print(words)

## del words['window'] ## TypeError: list indices must be integers or slices, not str

for i in range(5):
    print(i)

a = ['Mary', 'had', 'a', 'little', 'lamb']
for i in range(len(a)):  # combine lenth of list/array to find index and value
    print(i, a[i])

print(range(10))  # range(0, 10), strange result

print(sum(range(10)))  # sums up
print(list(range(10)))  # creates list

lst = range(10)
print(lst)  # range(0, 10), strange result

# else can be associated with loop as well
def primeNum():
    for n in range(2, 10):
        for x in range(2, n):
            if n % x == 0:
                print(n, 'equals', x, '*', n//x)
                break
        else:
            # loop fell through without finding a factor
            print(n, 'is a prime number')

primeNum();

## The pass statement does nothing.
# It can be used when a statement is required
# syntactically but the program requires no action

#while True:
#    pass

# default argument values

def ask_ok(prompt, retries=4, reminder='Please try again!'):
    while True:
        ok = input(prompt)
        if ok in ('y', 'ye', 'yes'):
            return True
        if ok in ('n', 'no', 'nop', 'nope'):
            return False
        retries = retries - 1
        if retries < 0:
            raise ValueError('invalid user response')
        print(reminder);

ask_ok('Do you really want to quit?');
# ask_ok('Do you really want to quit?')
# ask_ok('Do you really want to quit?', 2)
# ask_ok('Do you really want to quit?', 2 , 'another value')

i = 6;
def f(arg = i):
    print(arg)

f();

def parrot(voltage, state='a stiff', action='voom'):
    print("-- This parrot wouldn't", action, end=' ')
    print("if you put", voltage, "volts through it.", end=' ')
    print("E's", state, "!")

d = {"voltage": "four million", "state": "bleedin' demised", "action": "VOOM"}
parrot(**d); # replaces complete key values in print

"""
Small anonymous functions can be created with the lambda keyword.
"""
def make_incrementor(n):
    return lambda x: x + n

f = make_incrementor(42)
print(f(0))

print(f(1));

pairs = [(1, 'one'), (2, 'two'), (3, 'three'), (4, 'four')];
pairs.sort(key=lambda pair: pair[1]);
print(pairs);

"""
LIST METHODS:

list.append(x)
list.insert(i, x)
list.remove(x)
list.pop([i]) // if no i then last element
list.clear() OR del a[:]
list.index(x[, start[, end]]) // index of first occurrence
list.count(x)
list.sort(*, key=None, reverse=False)
list.reverse()
list.copy()

// use append(x) and pop() methods to make LIST as STACK. 

"""
## tuples
t = 12345, 54321, 'hello!'
print(t[0])

print(t)

# Tuples may be nested:
u = t, (1, 2, 3, 4, 5)
print(u)

# Tuples are immutable:
#t[0] = 88888 # won't change


# but they can contain mutable objects:
v = ([1, 2, 3], [3, 2, 1])
print(v);

"""
Curly braces {} or the set() function can be used to create sets. 
Note: to create an empty set you have to use set(), not {}
"""
set = {1,4,2,4}
print(set)
print(set.pop()) # top element
print(2 in set)

#a = set('sets') # creates empty set
#print(a)

"""
Dictionaries: key : value
"""
tel = {'jack': 4098, 'sape': 4139}
print(tel)

del tel['jack']
sorted(tel)
dct = dict([('sape', 4139), ('guido', 4127), ('jack', 4098)])

print(dct)

dct.get('jack')

# looping through dictionaries
knights = {'gallahad': 'the pure', 'robin': 'the brave'}

for k,v in knights.items():
    print(k,v)

# with indices
for i, v in enumerate(['tic', 'tac', 'toe']):
    print(i, v)

# loop over two or more sequences at the same time, the entries can be paired with the zip() function
questions = ['name', 'quest', 'favorite color']
answers = ['lancelot', 'the holy grail', 'blue']

for q, a in zip(questions, answers):
    print('What is your {0}?  It is {1}.'.format(q, a))

for i in sorted(questions):
    print(i)

## Using set() on a sequence eliminates duplicate elements
#for f in sorted(set(questions)):
 #   print(f)

"""
MODULES:

import <module-name>
from module import *
from module import abc
import module as fib
from module import fib as fibs

if __name__ == "__main__":
    do opns
    
"""

"""
PACKAGE/MODULE PROJECT STRUCTURE:

sound/                          Top-level package
      __init__.py               Initialize the sound package
      formats/                  Subpackage for file format conversions
              __init__.py
              wavread.py
              wavwrite.py
              aiffread.py
              aiffwrite.py
              auread.py
              auwrite.py
              ...
      effects/                  Subpackage for sound effects
              __init__.py
              echo.py
              surround.py
              reverse.py
              ...
      filters/                  Subpackage for filters
              __init__.py
              equalizer.py
              vocoder.py
              karaoke.py
"""

# formatted string literals: f is used before the string

print(f'The value of pi is approximately {239847283:.3f}.')

"""
Reading files:

f = open('workfile', 'w')

with open('workfile') as f:
    read_data = f.read()
    
f.closed;

f.readline();

for line in f:

f.write('This is a test\n');

"""

"""
EXCEPTION HANDLING
"""

while True:
    try:
        x = int(input("Please enter a number: "))
        break
    except ValueError: # OR except (RuntimeError, TypeError, NameError):
        print("Oops!  That was no valid number.  Try again...")
        # OR raise NameError('HiThere')

"""
CLASSES

"""

class Complex:
    def __init__(self, realpart, imagpart):
        self.r = realpart
        self.i = imagpart

x = Complex(3.0, -4.5)
x.r, x.i

"""
class DerivedClassName(BaseClassName):
    <statement-1>
    .
    .
    .
    <statement-N>
    
        OR
        
class DerivedClassName(Base1, Base2, Base3):
    <statement-1>
    .
    .
    .
    <statement-N>

PRIVATE VARIABLE: a name prefixed with an underscore (e.g. _spam)

__update = update   # private copy of original update() method

"""

"""

UTILITIES:

import os
import shutil
import glob
import sys
import argparse

import re
re.findall(r'\bf[a-z]*', 'which foot or hand fell fastest')

import math
import random
import statistics
from datetime import date

import logging
logging.debug('Debugging information')
logging.info('Informational message')
logging.warning('Warning:config file %s not found', 'server.conf')
logging.error('Error occurred')
logging.critical('Critical error -- shutting down')

GC: The memory is freed shortly after the last reference to it has been eliminated.

"""
