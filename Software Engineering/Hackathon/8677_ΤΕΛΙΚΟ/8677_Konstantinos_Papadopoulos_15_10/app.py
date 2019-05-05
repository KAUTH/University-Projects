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

node2 = Talk()
node2.setParameters(sentence = "Hello, welcome to sky net!!!", override = False, volume = 80)
node4 = Forker()
node3 = Joiner()
node7 = Talk()
node7.setParameters(sentence = "Goodbye, you have been erased (Arnold Schwatzeineger reference)!", override = False, volume = 80)
node8 = Stop()
bodyParts = []
bodyParts.append("HEAD")
node5_0 = DetectTouch()
node5_0.setParameters(duration = -1, parts = bodyParts)
node5_1 = Talk()
node5_1.setParameters(sentence = "You touched my head, you will be terminated!", override = False, volume = 80)
node5_GroupStart = Null()
node5_GroupFinish = Null()
node6_0 = RandomSelection()
node6_1 = BodyMove()
node6_1.setParameters(orientation = 'FORWARDS', duration = 5.0, distance = 0.0, speed = 0.0, safety = 0.3)
node6_2 = BodyMove()
node6_2.setParameters(orientation = 'BACKWARDS', duration = 3.0, distance = 0.0, speed = 0.0, safety = 0.3)
node6_3 = BodyMove()
node6_3.setParameters(orientation = 'RIGHT', duration = 0.0, distance = 2.0, speed = 0.0, safety = 0.3)
node6_4 = BodyMove()
node6_4.setParameters(orientation = 'LEFT', duration = 0.0, distance = 1.0, speed = 0.0, safety = 0.3)
node6_5 = RandomSelection()
node6_6 = Talk()
node6_6.setParameters(sentence = "I will detect for motion...acurrately, like a bat, with sonars.", override = False, volume = 80)
node6_7 = DetectMotion()
node6_7.setParameters(duration = -1)
node6_8 = Talk()
node6_8.setParameters(sentence = "Something moved, ohh the horror.", override = False, volume = 80)
node6_GroupStart = Null()
node6_GroupFinish = Null()

node5 = Composer()
node6 = Composer()
node5.setInternalStates([node5_GroupStart, node5_GroupFinish, node5_0, node5_1])
node5.setInitialState(node5_0)
node6.setInternalStates([node6_GroupStart, node6_GroupFinish, node6_0, node6_1, node6_2, node6_3, node6_4, node6_5, node6_6, node6_7, node6_8])
node6.setInitialState(node6_0)

node2.setName(name = 'node2')
node3.setName(name = 'node3')
node4.setName(name = 'node4')
node5.setName(name = 'node5')
node6.setName(name = 'node6')
node7.setName(name = 'node7')
node8.setName(name = 'node8')
node5_0.setName(name = 'node5_0')
node5_1.setName(name = 'node5_1')
node5_GroupStart.setName(name = 'node5_GroupStart')
node5_GroupFinish.setName(name = 'node5_GroupFinish')
node6_0.setName(name = 'node6_0')
node6_1.setName(name = 'node6_1')
node6_2.setName(name = 'node6_2')
node6_3.setName(name = 'node6_3')
node6_4.setName(name = 'node6_4')
node6_5.setName(name = 'node6_5')
node6_6.setName(name = 'node6_6')
node6_7.setName(name = 'node6_7')
node6_8.setName(name = 'node6_8')
node6_GroupStart.setName(name = 'node6_GroupStart')
node6_GroupFinish.setName(name = 'node6_GroupFinish')



node2.setNextStates([node4])

onDetectDictionary = []
node2.setPreemptions(onStart = [], onStop = [], onDetect = onDetectDictionary, onNotDetect = [])

onDetectDictionary = []
node3.setPreemptions(onStart = [], onStop = [], onDetect = onDetectDictionary, onNotDetect = [])
node4.setNextStates([node3])
node4.setForkedStates([node6 , node5 ])
node3.setNextStates([node7])
node3.setJoinedStates([node5, node6])

onDetectDictionary = []
node4.setPreemptions(onStart = [], onStop = [], onDetect = onDetectDictionary, onNotDetect = [])
node5.setNextStates([node3])

onDetectDictionary = []
node5.setPreemptions(onStart = [], onStop = [], onDetect = onDetectDictionary, onNotDetect = [])
node6.setNextStates([node3])

onDetectDictionary = []
node6.setPreemptions(onStart = [], onStop = [], onDetect = onDetectDictionary, onNotDetect = [])
node7.setNextStates([node8])

onDetectDictionary = []
node7.setPreemptions(onStart = [], onStop = [], onDetect = onDetectDictionary, onNotDetect = [])
node8.setNextStates(None)

onDetectDictionary = []
node8.setPreemptions(onStart = [], onStop = [], onDetect = onDetectDictionary, onNotDetect = [])
node5_0.setNextStates([node5_1, node5_0])

bodyParts = []
onDetectDictionary = {"HEAD": []}

node5_0.setPreemptions(onStart = [], onStop = [], onDetect = onDetectDictionary, onNotDetect = [])
node5_1.setNextStates([node5_GroupFinish])

onDetectDictionary = []
node5_1.setPreemptions(onStart = [], onStop = [], onDetect = onDetectDictionary, onNotDetect = [])
node5_GroupStart.setNextStates([node5_0])

onDetectDictionary = []
node5_GroupStart.setPreemptions(onStart = [], onStop = [], onDetect = onDetectDictionary, onNotDetect = [])
node5_GroupFinish.setNextStates(None)

onDetectDictionary = []
node5_GroupFinish.setPreemptions(onStart = [], onStop = [], onDetect = onDetectDictionary, onNotDetect = [])
node6_0.setNextStates([node6_1, node6_2, node6_3, node6_4])

onDetectDictionary = []
node6_0.setPreemptions(onStart = [], onStop = [], onDetect = onDetectDictionary, onNotDetect = [])
node6_1.setNextStates([node6_5])

onDetectDictionary = []
node6_1.setPreemptions(onStart = [], onStop = [], onDetect = onDetectDictionary, onNotDetect = [])
node6_2.setNextStates([node6_5])

onDetectDictionary = []
node6_2.setPreemptions(onStart = [], onStop = [], onDetect = onDetectDictionary, onNotDetect = [])
node6_3.setNextStates([node6_5])

onDetectDictionary = []
node6_3.setPreemptions(onStart = [], onStop = [], onDetect = onDetectDictionary, onNotDetect = [])
node6_4.setNextStates([node6_5])

onDetectDictionary = []
node6_4.setPreemptions(onStart = [], onStop = [], onDetect = onDetectDictionary, onNotDetect = [])
node6_5.setNextStates([node6_0, node6_6])

onDetectDictionary = []
node6_5.setPreemptions(onStart = [], onStop = [], onDetect = onDetectDictionary, onNotDetect = [])
node6_6.setNextStates([node6_7])

onDetectDictionary = []
node6_6.setPreemptions(onStart = [], onStop = [], onDetect = onDetectDictionary, onNotDetect = [])
node6_7.setNextStates([node6_8, node6_7])

onDetectDictionary = []
node6_7.setPreemptions(onStart = [], onStop = [], onDetect = onDetectDictionary, onNotDetect = [])
node6_8.setNextStates([node6_GroupFinish])

onDetectDictionary = []
node6_8.setPreemptions(onStart = [], onStop = [], onDetect = onDetectDictionary, onNotDetect = [])
node6_GroupStart.setNextStates([node6_0])

onDetectDictionary = []
node6_GroupStart.setPreemptions(onStart = [], onStop = [], onDetect = onDetectDictionary, onNotDetect = [])
node6_GroupFinish.setNextStates(None)

onDetectDictionary = []
node6_GroupFinish.setPreemptions(onStart = [], onStop = [], onDetect = onDetectDictionary, onNotDetect = [])


node2.execute()

