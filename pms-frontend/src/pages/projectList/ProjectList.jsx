import { Button } from '@/components/ui/button'
import { Card, CardContent } from '@/components/ui/card'
import { Input } from '@/components/ui/input'
import { Label } from '@/components/ui/label'
import { RadioGroup, RadioGroupItem } from '@/components/ui/radio-group'
import { ScrollArea } from '@/components/ui/scroll-area'
import { MagnifyingGlassIcon, MixerHorizontalIcon } from '@radix-ui/react-icons'
import { useEffect, useState } from 'react'
import ProjectCard from '../project/ProjectCard'
import { useDispatch, useSelector } from 'react-redux'
import { fetchProjects, searchProjects } from '@/redux/Project/Action'

const category=[
    "all",
    "fullstack",
    "frontend",
    "backend",
];

export const tags=[
    "all",
    "react",
    "nextjs",
    "spring boot",
    "mysql",
    "mongodb",
    "postgresql",
    "angular",
    "python",
    "java",
    "flask",
    "django",
    "typescript",
    "javascript",
    "html",
    "css",
];

const ProjectList = () => {
    const [keyword, setKeyword]= useState("");
    const {project} = useSelector(store => store)
    const dispatch = useDispatch()

    const handleFilterCategory = (value) => {
        if(value=="all"){
            dispatch(fetchProjects({}))
        } else {
            dispatch(fetchProjects({category:value}))
        }
    };

    const handleFilterTags = (value) => {
        if(value=="all"){
            dispatch(fetchProjects({}))
        } else {
            dispatch(fetchProjects({tag:value}))
        }
    };

    const handleSearchChange=(e)=>{
        setKeyword(e.target.value)
        dispatch(searchProjects(e.target.value))
    };

   

  return (
    <>
    <div className='relative px-5 lg:px-0 lg:flex gap-5 justify-center py-5'>
        <section className='filter section'>
            <Card className='p-5 sticky top-10'>

                <div className='flex justify-between lg:w-[20rem]'>
                    <p className='text-x1 -tracking-wider'>filters</p>
                    <Button variant='ghost' size='icon'>
                        <MixerHorizontalIcon/>
                    </Button>

                </div>

                <CardContent className='mt-5'>
                    <ScrollArea className='space-y-7 h-[70vh]'>
                        <div>
                            <h1 className='pb-3 text-gray-400 border-b'>
                                Category
                            </h1>
                            <div className='pt-5'>
                                <RadioGroup className='space-y-3 pt-5' defaultvalue='all' onValueChange={(value)=>handleFilterCategory(value)}>
                                    {category.map((item)=> (
                                        <div key={item} className='flex items-center gap-2'>
                                            <RadioGroupItem value={item} id={'r1-${item}'} />
                                            <Label htmlFor={'r1-${item}'}>{item}</Label>
                                        </div>
                                    ))}
                                    
                                </RadioGroup>
                            </div>
                        </div>
                        <div className='pt-9'>
                            <h1 className='pb-3 text-gray-400 border-b'>
                                Tag
                            </h1>
                            <div className='pt-5'>
                                <RadioGroup className='space-y-3 pt-5' defaultvalue='all' onValueChange={(value)=>handleFilterTags(value)}>
                                    {tags.map((item)=> (
                                        <div key={item} className='flex items-center gap-2'>
                                            <RadioGroupItem value={item} id={'r1-${item}'} />
                                            <Label htmlFor={'r1-${item}'}>{item}</Label>
                                        </div>
                                    ))}
                                    
                                </RadioGroup>
                            </div>
                        </div>
                    </ScrollArea>
                </CardContent>

            </Card>
        </section>
        <section className='projectListSection w-full lg:w-[48rem]'>
            <div className='flex gap-2 items-center pb-5 justify-between'>
                <div className='relative p-0 w-full'>
                    <Input 
                    onChange={handleSearchChange} 
                    placeholder='Search Project' 
                    className='40% px-9' />
                    <MagnifyingGlassIcon className='absolute top-3 left-4' />
                </div>
            </div>
            <div>
                <div className='space-y-5 min-h-[74vh]'>
                    {
                        keyword?project.searchProjects?.map((item)=> <ProjectCard item = {item} key={item.id+10}/>):
                        project.projects?.map((item)=> (
                        <ProjectCard key={item.id} item={item}/>
                    ))}
                </div>
            </div>

        </section>
    </div>
    </>
  )
}

export default ProjectList