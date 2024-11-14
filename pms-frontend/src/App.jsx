import { useEffect, useState } from 'react'
import './App.css'
import Home from './pages/home/Home'
import Navbar from './pages/navbar/Navbar'
import { Route, Routes } from 'react-router-dom'
import ProjectDetails from './pages/projectDetails/ProjectDetails'
import IssueDetails from './pages/issueDetails/IssueDetails'
import Auth from './pages/Auth/Auth'
import { useDispatch, useSelector } from 'react-redux'
import { getUser } from './redux/Auth/Action'

import { fetchProjects } from './redux/Project/Action'

function App() {
  const dispatch = useDispatch();
  const {auth} = useSelector(store=>store)

  useEffect(()=>{
    dispatch(getUser())
    dispatch(fetchProjects({}))
  },[auth.jwt])

  console.log(auth)

 
  return (
    <>
    
    {
      auth.user?<div>
      <Navbar/>
    <Routes>
      <Route path='/' element={<Home/>}/>
      <Route path='/project/:id' element={<ProjectDetails/>}/>
      <Route path='/project/:projectId/issue/:issueId' element={<IssueDetails/>}/>
    </Routes>
    </div>:<Auth/>
    }
    
    </>
  )
}

export default App
