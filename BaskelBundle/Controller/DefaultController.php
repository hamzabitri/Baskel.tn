<?php

namespace BaskelBundle\Controller;

use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Symfony\Component\HttpFoundation\Request;

class DefaultController extends Controller
{
    public function indexAction(Request $request)
    {

        $em = $this->getDoctrine()->getManager();
        $produit = $em->getRepository('BaskelBundle:Produit')->findAll();
        $categoriep = $em->getRepository('BaskelBundle:CategorieP')->findAll();

        $paginator = $this->get('knp_paginator');
        $result = $paginator->paginate(
            $produit,
            $request->query->getInt('page', 1),
            $request->query->getInt('limit', 4)
        );


//...

        return $this->render('@Baskel/Produit/afficheP.html.twig', array(
            'produit' => $result, 'categoriep' => $categoriep));
    }
}
