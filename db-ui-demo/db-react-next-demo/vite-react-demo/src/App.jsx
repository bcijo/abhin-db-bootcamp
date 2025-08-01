import { useState } from 'react'
import './App.css'
import EmployeeDetails from './components/employee/EmployeeDetails.jsx'

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

        <EmployeeDetails></EmployeeDetails>
    </>
  )
}

export default App
