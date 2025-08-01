import { useState } from 'react'
import './App.css'
import EmployeeList from './components/employee/EmployeeList.jsx'

function MyButton() {
  return (
    <button>I'm a button</button>
  );
}

function App() {
  const [count, setCount] = useState(0)

  return (
    <>
      <p>Hello world!</p>
        <MyButton />

        <EmployeeList></EmployeeList>

        <img src="https://logowik.com/content/uploads/images/deutsche-bank5748.logowik.com.webp"></img>
    </>
  )
}

export default App
