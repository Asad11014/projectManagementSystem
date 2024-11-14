import { Avatar, AvatarFallback } from "@/components/ui/avatar"
import { Button } from "@/components/ui/button"
import { Form, FormControl, FormField, FormItem, FormMessage } from "@/components/ui/form"
import { Input } from "@/components/ui/input"
import { createComment } from "@/redux/Comment/Action"
import { useForm } from "react-hook-form"
import { useDispatch } from "react-redux"

const CreateCommentForm = ({issueId}) => {
    const dispatch = useDispatch()
    const form = useForm({
        //resolver:zod
        defaultValues:{
            content: "",
        }
    })

    const onSubmit = (data)=>{
        dispatch(createComment({content:data.content,issueId}))
        console.log('create project data', data)
    }
  return (
    <div>
        <Form {...form}>
            <form className="flex gap-2" onSubmit = {form.handleSubmit(onSubmit)}>
                <FormField control={form.control} name='content' render={({field})=> (
                <FormItem>

                    <div className="flex gap-2">
                        <div>
                        <Avatar>
                            <AvatarFallback>A</AvatarFallback>
                        </Avatar>
                    </div>
                    <FormControl>
                        <Input {...field} 
                        type='text' 
                        className='w-[20rem]' 
                        placeholder='Add Comment...' />
                    </FormControl>
                    </div>
                    
                    <FormMessage/>
                </FormItem>
                )}/>

                
                
                <Button type='submit'>
                    Save
                </Button>
               
            </form>
        </Form>

    </div>
  )
}

export default CreateCommentForm