<template>
  <div class="calculator">
    <div class="display">{{current || '0'}}</div>
    <div @click="clear" class="btn">C</div>
    <div @click="sign" class="btn">+/-</div>
    <div @click="percent" class="btn">%</div>
    <div @click="divide" class="btn operator">÷</div>
    <div @click="append('7')" class="btn">7</div>
    <div @click="append('8')" class="btn">8</div>
    <div @click="append('9')" class="btn">9</div>
    <div @click="multiply" class="btn operator">x</div>
    <div @click="append('4')" class="btn">4</div>
    <div @click="append('5')" class="btn">5</div>
    <div @click="append('6')" class="btn">6</div>
    <div @click="minus" class="btn operator">-</div>
    <div @click="append('1')" class="btn">1</div>
    <div @click="append('2')" class="btn">2</div>
    <div @click="append('3')" class="btn">3</div>
    <div @click="plus" class="btn operator">+</div>
    <div @click="append('0')" class="btn zero">0</div>
    <div @click="dot" class="btn">.</div>
    <div @click="equals" class="btn operator">=</div>
    <textarea id="equationLog"></textarea>
    

  </div>
</template>

<script>
export default {
  data() {
    return {
      previous: null,
      current: '',
      operator: null, 
      operatorClicked: false,
      equation: '',
      symbol: '',
    }
  },
  methods: {
    clear() {
      this.current = '';
    },
    sign() {
      this.current = this.current.charAt(0) === '-' ?
       this.current.slice(1) : `-${this.current}`
    },
    percent() {
      this.current = `${parseFloat(this.current) / 100}`
    },
    append(number) {
      if(this.operatorClicked) {
        this.current = '';
        this.operatorClicked = false;
      }
      this.current = `${this.current}${number}`;
    },
    dot() {
      if(this.current.indexOf('.') === -1)
        this.append('.');
    },
    setPrevious() {
      this.previous = this.current;
      this.operatorClicked = true;
    },

    divide() {
      this.setPrevious();
      this.symbol = " ÷ ";
      
    },
    multiply() {
      this.setPrevious();
      this.symbol = " x ";
      
    },
    minus() {
      this.setPrevious();
      this.symbol = " - ";
      
    },
    plus() {
      this.setPrevious();
      this.symbol = " + ";
      
    },
    async equals() {
      this.equation = this.previous + this.symbol + this.current;

      let input = {
        number1: this.previous,
        number2: this.current,
        operator: this.symbol
      };

      let url = "http://localhost:8080/calculate/equation";
      let options = {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(input),
      };

      await fetch(url, options)
        .then((response) => response.json())
        .then((data) => {
          if(data) {
            console.log(data)
            this.current = data;
          }else {
              console.log("Invalid equation")
          }
        })
        .catch((error) => {
          console.log(error);
        });

      /*
      this.current = `${this.operator(
        parseFloat(this.previous), 
        parseFloat(this.current)
      )}`;
      */

      this.equation += " = " + this.current + "\n";
      this.previous = null;
      document.getElementById("equationLog").innerHTML += this.equation + "\n";
      this.operatorClicked = true;
    },
  }
}
</script>

<style scoped>
.calculator {
  width: 500px;
  margin: 0 auto;
  display: grid;
  font-size: 40px;
  grid-template-columns: repeat(4, 1fr);
  grid-auto-rows: minmax(50px, auto);
}

.display {
  grid-column: 1 / 5;
  background-color: grey;
  color: white;
}

.zero {
  grid-column: 1 / 3;
}

.btn {
  background-color: #121212;
  border: 1px solid #333;
  color: white;
}

.operator {
  background-color: orange;

}

.equationLog {
  grid-column: 1 / 4;
  margin: 0 auto;
}

#equationLog {
  grid-column: 1 / 5;
  height: 300px;
  font-size: 20px;
  overflow-y: scroll;
}
</style>

