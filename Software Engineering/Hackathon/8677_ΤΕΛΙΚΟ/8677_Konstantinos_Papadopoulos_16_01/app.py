#!/usr/bin/python2
# -*- coding: utf-8 -*-

import time

from Behaviors.BArmMotion import ArmMotion
from Behaviors.BBodyMove import BodyMove
from Behaviors.BComposer import Composer
from Behaviors.BCounter import Counter
from Behaviors.BDebug import DBG
from Behaviors.BDetectHuman import DetectHuman
from Behaviors.BDetectMotion import DetectMotion
from Behaviors.BDetectSound import DetectSound
from Behaviors.BDetectTouch import DetectTouch
from Behaviors.BForker import Forker
from Behaviors.BHeadMotion import HeadMotion
from Behaviors.BJoiner import Joiner
from Behaviors.BLearnMotion import LearnMotion
from Behaviors.BListen import Listen
from Behaviors.BPosture import Posture
from Behaviors.BRandomSelection import RandomSelection
from Behaviors.BReplayMotion import ReplayMotion
from Behaviors.BStop import Stop
from Behaviors.BTakePicture import TakePicture
from Behaviors.BTalk import Talk
from Behaviors.BWeatherReport import WeatherReport
from Behaviors.BDelay import Delay
from Behaviors.BNull import Null
from Behaviors.BLeds import Leds
from Behaviors.BPlayAudio import PlayAudio
from Behaviors.BRecord import Record

node2 = Posture()
node2.setParameters(posture = 'STAND')
node3 = Talk()
node3.setParameters(sentence = "Hey, I am from the future, give everyone here 1 grade!!", override = False, volume = 100)
node5 = Forker()
node4 = Joiner()
node9 = Forker()
node8 = Joiner()
node10 = Talk()
node10.setParameters(sentence = "I will now demonstrate more complex capabilities and start threads C, D, E, with a loud voice!", override = False, volume = 100)
node14 = Talk()
node14.setParameters(sentence = "Goodbye, one more challenge to go!", override = False, volume = 100)
node15 = Stop()
node6_0 = RandomSelection()
node6_1 = BodyMove()
node6_1.setParameters(orientation = 'FORWARDS', duration = 0.0, distance = 1.0, speed = 0.0, safety = 0.3)
node6_2 = BodyMove()
node6_2.setParameters(orientation = 'BACKWARDS', duration = 0.0, distance = 1.0, speed = 0.0, safety = 0.3)
node6_3 = BodyMove()
node6_3.setParameters(orientation = 'LEFT', duration = 0.0, distance = 1.0, speed = 0.0, safety = 0.3)
node6_4 = BodyMove()
node6_4.setParameters(orientation = 'RIGHT', duration = 0.0, distance = 1.0, speed = 0.0, safety = 0.3)
node6_GroupStart = Null()
node6_GroupFinish = Null()
node7_GroupStart = Null()
node7_GroupFinish = Null()
node11_GroupStart = Null()
node11_GroupFinish = Null()
node12_GroupStart = Null()
node12_GroupFinish = Null()
node13_GroupStart = Null()
node13_GroupFinish = Null()

node6 = Composer()
node7 = Composer()
node11 = Composer()
node12 = Composer()
node13 = Composer()
node6.setInternalStates([node6_GroupStart, node6_GroupFinish, node6_0, node6_1, node6_2, node6_3, node6_4])
node6.setInitialState(node6_0)
node7.setInternalStates([node7_GroupStart, node7_GroupFinish, ])
node7.setInitialState(node)
node11.setInternalStates([node11_GroupStart, node11_GroupFinish, ])
node11.setInitialState(node)
node12.setInternalStates([node12_GroupStart, node12_GroupFinish, ])
node12.setInitialState(node)
node13.setInternalStates([node13_GroupStart, node13_GroupFinish, ])
node13.setInitialState(node)

node2.setName(name = 'node2')
node3.setName(name = 'node3')
node4.setName(name = 'node4')
node5.setName(name = 'node5')
node6.setName(name = 'node6')
node7.setName(name = 'node7')
node8.setName(name = 'node8')
node9.setName(name = 'node9')
node10.setName(name = 'node10')
node11.setName(name = 'node11')
node12.setName(name = 'node12')
node13.setName(name = 'node13')
node14.setName(name = 'node14')
node15.setName(name = 'node15')
node6_0.setName(name = 'node6_0')
node6_1.setName(name = 'node6_1')
node6_2.setName(name = 'node6_2')
node6_3.setName(name = 'node6_3')
node6_4.setName(name = 'node6_4')
node6_GroupStart.setName(name = 'node6_GroupStart')
node6_GroupFinish.setName(name = 'node6_GroupFinish')
node7_GroupStart.setName(name = 'node7_GroupStart')
node7_GroupFinish.setName(name = 'node7_GroupFinish')
node11_GroupStart.setName(name = 'node11_GroupStart')
node11_GroupFinish.setName(name = 'node11_GroupFinish')
node12_GroupStart.setName(name = 'node12_GroupStart')
node12_GroupFinish.setName(name = 'node12_GroupFinish')
node13_GroupStart.setName(name = 'node13_GroupStart')
node13_GroupFinish.setName(name = 'node13_GroupFinish')



node2.setNextStates([node3])

onDetectDictionary = []
node2.setPreemptions(onStart = [], onStop = [], onDetect = onDetectDictionary, onNotDetect = [])
node3.setNextStates([node5])

onDetectDictionary = []
node3.setPreemptions(onStart = [], onStop = [], onDetect = onDetectDictionary, onNotDetect = [])

onDetectDictionary = []
node4.setPreemptions(onStart = [], onStop = [], onDetect = onDetectDictionary, onNotDetect = [])
node5.setNextStates([node4])
node5.setForkedStates([node6 , node7 ])
node4.setNextStates([node10])
node4.setJoinedStates([node6, node7])

onDetectDictionary = []
node5.setPreemptions(onStart = [], onStop = [], onDetect = onDetectDictionary, onNotDetect = [])
node6.setNextStates([node4])

onDetectDictionary = []
node6.setPreemptions(onStart = [], onStop = [], onDetect = onDetectDictionary, onNotDetect = [])
node7.setNextStates([node4])

onDetectDictionary = []
node7.setPreemptions(onStart = [], onStop = [], onDetect = onDetectDictionary, onNotDetect = [])

onDetectDictionary = []
node8.setPreemptions(onStart = [], onStop = [], onDetect = onDetectDictionary, onNotDetect = [])
node9.setNextStates([node8])
node9.setForkedStates([node11 , node12 , node13 ])
node8.setNextStates([node14])
node8.setJoinedStates([node11, node12, node13])

onDetectDictionary = []
node9.setPreemptions(onStart = [], onStop = [], onDetect = onDetectDictionary, onNotDetect = [])
node10.setNextStates([node9])

onDetectDictionary = []
node10.setPreemptions(onStart = [], onStop = [], onDetect = onDetectDictionary, onNotDetect = [])
node11.setNextStates([node8])

onDetectDictionary = []
node11.setPreemptions(onStart = [], onStop = [], onDetect = onDetectDictionary, onNotDetect = [])
node12.setNextStates([node8])

onDetectDictionary = []
node12.setPreemptions(onStart = [], onStop = [], onDetect = onDetectDictionary, onNotDetect = [])
node13.setNextStates([node8])

onDetectDictionary = []
node13.setPreemptions(onStart = [], onStop = [], onDetect = onDetectDictionary, onNotDetect = [])
node14.setNextStates([node15])

onDetectDictionary = []
node14.setPreemptions(onStart = [], onStop = [], onDetect = onDetectDictionary, onNotDetect = [])
node15.setNextStates(None)

onDetectDictionary = []
node15.setPreemptions(onStart = [], onStop = [], onDetect = onDetectDictionary, onNotDetect = [])
node6_0.setNextStates([node6_1, node6_2, node6_3, node6_4])

onDetectDictionary = []
node6_0.setPreemptions(onStart = [], onStop = [], onDetect = onDetectDictionary, onNotDetect = [])
node6_1.setNextStates(None)

onDetectDictionary = []
node6_1.setPreemptions(onStart = [], onStop = [], onDetect = onDetectDictionary, onNotDetect = [])
node6_2.setNextStates(None)

onDetectDictionary = []
node6_2.setPreemptions(onStart = [], onStop = [], onDetect = onDetectDictionary, onNotDetect = [])
node6_3.setNextStates(None)

onDetectDictionary = []
node6_3.setPreemptions(onStart = [], onStop = [], onDetect = onDetectDictionary, onNotDetect = [])
node6_4.setNextStates(None)

onDetectDictionary = []
node6_4.setPreemptions(onStart = [], onStop = [], onDetect = onDetectDictionary, onNotDetect = [])
node6_GroupStart.setNextStates([node6_0])

onDetectDictionary = []
node6_GroupStart.setPreemptions(onStart = [], onStop = [], onDetect = onDetectDictionary, onNotDetect = [])
node6_GroupFinish.setNextStates(None)

onDetectDictionary = []
node6_GroupFinish.setPreemptions(onStart = [], onStop = [], onDetect = onDetectDictionary, onNotDetect = [])
node7_GroupStart.setNextStates(None)

onDetectDictionary = []
node7_GroupStart.setPreemptions(onStart = [], onStop = [], onDetect = onDetectDictionary, onNotDetect = [])
node7_GroupFinish.setNextStates(None)

onDetectDictionary = []
node7_GroupFinish.setPreemptions(onStart = [], onStop = [], onDetect = onDetectDictionary, onNotDetect = [])
node11_GroupStart.setNextStates(None)

onDetectDictionary = []
node11_GroupStart.setPreemptions(onStart = [], onStop = [], onDetect = onDetectDictionary, onNotDetect = [])
node11_GroupFinish.setNextStates(None)

onDetectDictionary = []
node11_GroupFinish.setPreemptions(onStart = [], onStop = [], onDetect = onDetectDictionary, onNotDetect = [])
node12_GroupStart.setNextStates(None)

onDetectDictionary = []
node12_GroupStart.setPreemptions(onStart = [], onStop = [], onDetect = onDetectDictionary, onNotDetect = [])
node12_GroupFinish.setNextStates(None)

onDetectDictionary = []
node12_GroupFinish.setPreemptions(onStart = [], onStop = [], onDetect = onDetectDictionary, onNotDetect = [])
node13_GroupStart.setNextStates(None)

onDetectDictionary = []
node13_GroupStart.setPreemptions(onStart = [], onStop = [], onDetect = onDetectDictionary, onNotDetect = [])
node13_GroupFinish.setNextStates(None)

onDetectDictionary = []
node13_GroupFinish.setPreemptions(onStart = [], onStop = [], onDetect = onDetectDictionary, onNotDetect = [])


node2.execute()

