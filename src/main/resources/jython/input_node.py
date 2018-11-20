
from com.clt.diamant.graph.nodes import AbstractInputNode
from com.clt.speech.recognition import LanguageName
from com.clt.speech.recognition import MatchResult

class JythonStdinInputNode(AbstractInputNode):
    def __init__(self):
        self._defaultLanguage = LanguageName("", None)

    def graphicallyRecognize(self, layer, recGrammar, patterns, timeout, confidenceThreshold, interactiveTest):
        trials = -1
        match = None

        while match is None and trials < timeout:
            recognitionResult = raw_input("Enter your input: ")
            match = AbstractInputNode.findMatch(recognitionResult, recGrammar, patterns)
            trials += 1

        if trials > timeout:
            print "reached timeout: " + timeout

        if interactiveTest:
            print "confirming result: " + match.getUtterance()

        return match
        
    def getAudioFormat(self):
        return None

    def createRecognitionExecutor(self, grammar):
        return None

    def getDevice(self):
        return None

    def getAvailableLanguages(self):
        return [self._defaultLanguage]

    def getDefaultLanguage(self):
        return self._defaultLanguage


